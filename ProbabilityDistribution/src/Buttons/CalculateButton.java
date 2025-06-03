package Buttons;

import DataHandler.Calculate;
import DataHandler.Data;
import Listeners.MouseActionListener;
import Listeners.MouseHoverListener;
import Measurements.Pos;
import Measurements.Scale;
import Panels.ImagePanel;
import Panels.MainPanel;
import Panels.Panel;
import Text.MessageGenerator;
import Timer.Timer;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;

public class CalculateButton implements Panel, MouseActionListener {

    public ImagePanel calculateButton = new ImagePanel("../resources/calculateButton.png");
    private MouseHoverListener mouseHoverListener = new MouseHoverListener(calculateButton);

    MainPanel mainPanel;

    MessageGenerator generateDontAddToOne = new MessageGenerator();
    MessageGenerator generateIncompleteAdded = new MessageGenerator();
    MessageGenerator generateEmpty = new MessageGenerator();
    MessageGenerator generateSuccess = new MessageGenerator();

    Timer timer;
    public final double MARGIN = 0.00005;

    public CalculateButton() {
        calculateButton.setBounds(Pos.CALC_BUTTON_X, Pos.CALC_BUTTON_Y, Scale.CONTROL_BUTTON_W, Scale.CONTROL_BUTTON_H);
        calculateButton.setLayout(null);

        mouseHoverListener.addOldValues(Pos.CALC_BUTTON_X, Pos.CALC_BUTTON_Y, Scale.CONTROL_BUTTON_W, Scale.CONTROL_BUTTON_H);
        mouseHoverListener.addNewValues(Pos.CALC_BUTTON_X - 2, Pos.CALC_BUTTON_Y - 2, Scale.CONTROL_BUTTON_W + 4, Scale.CONTROL_BUTTON_H + 4);
        mouseHoverListener.addOnHoverAction();
    }

    @Override
    public void addMouseActionListener() {
        calculateButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                calculateButton.setBounds(Pos.CALC_BUTTON_X, Pos.CALC_BUTTON_Y, Scale.CONTROL_BUTTON_W, Scale.CONTROL_BUTTON_H);
                calculateButtonAction();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                calculateButton.setBounds(Pos.CALC_BUTTON_X - 2, Pos.CALC_BUTTON_Y - 2, Scale.CONTROL_BUTTON_W + 4, Scale.CONTROL_BUTTON_H + 4);
            }
        });
    }


    public void addLabels() {
        int x = 210, y = 506, w = 400, h = 30;

        generateDontAddToOne.setFontSize(16);
        generateDontAddToOne.generate("Probabilities don't add up to 1", Color.RED, Color.decode("#efe8e8"));
        generateDontAddToOne.getLabel().setBounds(x - 45, y - 8, w, h);

        generateIncompleteAdded.setFontSize(16);
        generateIncompleteAdded.generate("Incomplete! Add all probabilities first!", Color.RED, Color.decode("#efe8e8"));
        generateIncompleteAdded.getLabel().setBounds(x - 75, y - 10, w, h);

        generateEmpty.setFontSize(16);
        generateEmpty.generate("Table is empty! Add outcomes and probabilities", Color.RED, Color.decode("#efe8e8"));
        generateEmpty.getLabel().setBounds(x - 118, y - 8, w, h);

        generateSuccess.setFontSize(16);
        generateSuccess.generate("Calculation success!", Color.GREEN, Color.decode("#efe8e8"));
        generateSuccess.getLabel().setBounds(x - 17, y - 8, w, h);


        mainPanel.getPanel().add(generateDontAddToOne.getLabel());
        mainPanel.getPanel().add(generateIncompleteAdded.getLabel());
        mainPanel.getPanel().add(generateEmpty.getLabel());
        mainPanel.getPanel().add(generateSuccess.getLabel());
    }


    public void offLabels() {
        generateDontAddToOne.getLabel().setVisible(false);
        generateIncompleteAdded.getLabel().setVisible(false);
        generateEmpty.getLabel().setVisible(false);
        generateSuccess.getLabel().setVisible(false);
    }


    public void calculateButtonAction() {
        mainPanel.undoButton.offLabels();
        offLabels();

        if (mainPanel.stack.empty()) {
            generateEmpty.getLabel().setVisible(true);

            timer = new Timer(3000, this::offLabels);
            timer.start();
            return;
        }

        if (mainPanel.stack.size() != mainPanel.numberOfOutcomes) {
            generateIncompleteAdded.getLabel().setVisible(true);

            timer = new Timer(2300, this::offLabels);
            timer.start();
            return;
        }


        int count = mainPanel.stack.size();
        double sum = 0;

        for (Data data : mainPanel.stack) {
            sum += data.probability;
        }

        if (sum < 1 - MARGIN || sum > 1) {
            generateDontAddToOne.getLabel().setVisible(true);

            timer = new Timer(2300, this::offLabels);
            timer.start();
            return;
        }


        double mean = Calculate.mean(mainPanel.stack);
        double variance = Calculate.variance(mainPanel.stack);
        double sd = Calculate.stdDv8tion(mainPanel.stack);


        DecimalFormat df = new DecimalFormat("0.####");

        mainPanel.meanTextField.getTextField().setForeground(Color.decode("#800404"));
        mainPanel.varTextField.getTextField().setForeground(Color.decode("#800404"));
        mainPanel.sdTextField.getTextField().setForeground(Color.decode("#800404"));

        mainPanel.meanTextField.getTextField().setText(String.valueOf(df.format(mean)));
        mainPanel.varTextField.getTextField().setText(String.format("%.4f", variance));
        mainPanel.sdTextField.getTextField().setText(String.format("%.4f", sd));

        mainPanel.meanTextField.getTextField().setVisible(true);
        mainPanel.varTextField.getTextField().setVisible(true);
        mainPanel.sdTextField.getTextField().setVisible(true);


        generateSuccess.getLabel().setVisible(true);
        timer = new Timer(2300, this::offLabels);
        timer.start();
    }


    @Override
    public ImagePanel getPanel() {return this.calculateButton;}
    public void passMainPanel(MainPanel mainPanel) {this.mainPanel = mainPanel;}

}

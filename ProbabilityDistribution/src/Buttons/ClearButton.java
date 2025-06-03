package Buttons;

import Listeners.MouseActionListener;
import Listeners.MouseHoverListener;
import Measurements.Pos;
import Measurements.Scale;
import Panels.ImagePanel;
import Panels.MainPanel;
import Panels.Panel;
import Text.MessageGenerator;
import Text.TextField;
import Timer.Timer;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ClearButton implements Panel, MouseActionListener {

    public ImagePanel clearButton = new ImagePanel("../resources/clearButton.png");
    private MouseHoverListener mouseHoverListener = new MouseHoverListener(clearButton);

    MainPanel mainPanel;
    Timer timer;

    MessageGenerator generateEmptyTable = new MessageGenerator();
    MessageGenerator generateSuccess = new MessageGenerator();

    public ClearButton() {
        clearButton.setBounds(Pos.CLEAR_BUTTON_X, Pos.CLEAR_BUTTON_Y, Scale.CONTROL_BUTTON_W, Scale.CONTROL_BUTTON_H);
        clearButton.setLayout(null);

        mouseHoverListener.addOldValues(Pos.CLEAR_BUTTON_X, Pos.CLEAR_BUTTON_Y, Scale.CONTROL_BUTTON_W, Scale.CONTROL_BUTTON_H);
        mouseHoverListener.addNewValues(Pos.CLEAR_BUTTON_X - 2, Pos.CLEAR_BUTTON_Y - 2, Scale.CONTROL_BUTTON_W + 4, Scale.CONTROL_BUTTON_H + 4);
        mouseHoverListener.addOnHoverAction();
    }

    @Override
    public void addMouseActionListener() {
        clearButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                clearButton.setBounds(Pos.CLEAR_BUTTON_X, Pos.CLEAR_BUTTON_Y, Scale.CONTROL_BUTTON_W, Scale.CONTROL_BUTTON_H);
                clearButtonAction();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                clearButton.setBounds(Pos.CLEAR_BUTTON_X - 2, Pos.CLEAR_BUTTON_Y - 2, Scale.CONTROL_BUTTON_W + 4, Scale.CONTROL_BUTTON_H + 4);
            }
        });
    }


    public void addLabels() {
        generateSuccess.setFontSize(16);
        generateSuccess.generate("Clearing success!", Color.GREEN, Color.decode("#efe8e8"));
        generateSuccess.getLabel().setBounds(210 - 118, 506 - 8, 400, 30);

        generateEmptyTable.setFontSize(16);
        generateEmptyTable.generate("Cannot clear! Table is empty!", Color.RED, Color.decode("#efe8e8"));
        generateEmptyTable.getLabel().setBounds(210 - 52, 506 - 8, 400, 30);

        mainPanel.getPanel().add(generateSuccess.getLabel());
        mainPanel.getPanel().add(generateEmptyTable.getLabel());
    }


    public void offLabels() {
        mainPanel.calculateButton.offLabels();
        mainPanel.undoButton.offLabels();
        generateSuccess.getLabel().setVisible(false);
        generateEmptyTable.getLabel().setVisible(false);
    }


    public void clearButtonAction() {
        offLabels();

        if (mainPanel.stack.empty()) {
            generateEmptyTable.getLabel().setVisible(true);
        }
        else {
            mainPanel.stack.clear();
            mainPanel.numberOfOutcomes = 0;
            mainPanel.addedOutcomes = 0;
            mainPanel.addButton.generateAddedOutcomes.getLabel().setVisible(false);
            mainPanel.outcomeProbabilityField.reprintTextArea();


            mainPanel.numOfOutcomesField.getTextField().setText("");
            mainPanel.numOfOutcomesField.getTextField().requestFocusInWindow();
            mainPanel.outcomesField.getTextField().setText("");
            mainPanel.outcomesField.getTextField().requestFocusInWindow();
            mainPanel.probabilityField.getTextField().setText("");
            mainPanel.probabilityField.getTextField().requestFocusInWindow();


            mainPanel.meanTextField.getTextField().setForeground(Color.decode("#a3a3a3"));
            mainPanel.meanTextField.getTextField().setText("Mean");
            mainPanel.meanTextField.getTextField().repaint();

            mainPanel.varTextField.getTextField().setForeground(Color.decode("#a3a3a3"));
            mainPanel.varTextField.getTextField().setText("Variance");
            mainPanel.varTextField.getTextField().repaint();

            mainPanel.sdTextField.getTextField().setForeground(Color.decode("#a3a3a3"));
            mainPanel.sdTextField.getTextField().setText("Standard Deviation");
            mainPanel.sdTextField.getTextField().repaint();


            mainPanel.getPanel().requestFocusInWindow();
            generateSuccess.getLabel().setVisible(true);
            mainPanel.getPanel().repaint();
        }

        timer = new Timer(2000, this::offLabels);
        timer.start();
    }


    @Override
    public ImagePanel getPanel() {return this.clearButton;}
    public void passMainPanel(MainPanel mainPanel) {this.mainPanel = mainPanel;}
}

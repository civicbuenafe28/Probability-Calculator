package Buttons;

import Listeners.EnterActionListener;
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


public class SetButton implements Panel, MouseActionListener, EnterActionListener {

    public ImagePanel setButton = new ImagePanel("../resources/setButton.png");
    private MouseHoverListener mouseHoverListener = new MouseHoverListener(this.setButton);
    MainPanel mainPanel;

    MessageGenerator generateSuccess = new MessageGenerator();
    MessageGenerator generateInvalidInput = new MessageGenerator();
    MessageGenerator generateMustBePositive = new MessageGenerator();
    MessageGenerator generateLessThanAdded = new MessageGenerator();
    MessageGenerator generateCannotSetOutcome = new MessageGenerator();

    Timer timer;

    public SetButton() {
        setButton.setBounds(Pos.SET_BUTTON_X, Pos.SET_BUTTON_Y, Scale.SET_ADD_W, Scale.SET_ADD_H);

        mouseHoverListener.addOldValues(Pos.SET_BUTTON_X, Pos.SET_BUTTON_Y, Scale.SET_ADD_W, Scale.SET_ADD_H);
        mouseHoverListener.addNewValues(Pos.SET_BUTTON_X - 2, Pos.SET_BUTTON_Y - 2, Scale.SET_ADD_W + 4, Scale.SET_ADD_H + 4);
        mouseHoverListener.addOnHoverAction();
    }


    @Override
    public void addMouseActionListener() {
        setButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                setButton.setBounds(Pos.SET_BUTTON_X, Pos.SET_BUTTON_Y, Scale.SET_ADD_W, Scale.SET_ADD_H);
                setButtonAction();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                setButton.setBounds(Pos.SET_BUTTON_X - 2, Pos.SET_BUTTON_Y - 2, Scale.SET_ADD_W + 4, Scale.SET_ADD_H + 4);
            }
        });
    }


    @Override
    public void addEnterActionListener(Text.TextField textField) {
        textField.getTextField().addActionListener(e -> setButtonAction());
    }


    public void addLabels() {
        int x = 105, y = 205, w = 250, h = 50;

        generateSuccess.getLabel().setBounds(x - 3, y + 4, w, h);
        generateSuccess.setFontSize(14);
        generateSuccess.generate("Successfully set no. of outcomes!", Color.GREEN, Color.WHITE);

        generateInvalidInput.getLabel().setBounds(x, y, w, h);
        generateInvalidInput.generate("Invalid input!", Color.RED, Color.WHITE);

        generateMustBePositive.getLabel().setBounds(x, y + 3, w, h);
        generateMustBePositive.generate("<html>Must be a non-zero<br>positive integer!</html>", Color.RED, Color.WHITE);

        generateLessThanAdded.getLabel().setBounds(x, y + 3, w, h);
        generateLessThanAdded.generate("<html>New no. of outcomes is<br>less than the added outcomes!</html>", Color.RED, Color.WHITE);

        generateCannotSetOutcome.getLabel().setBounds(x, y + 3, w, h);
        generateCannotSetOutcome.generate("<html>Cannot set no. of outcomes<br>A row contains a probability of 1</html>", Color.RED, Color.WHITE);

        mainPanel.getPanel().add(generateSuccess.getLabel());
        mainPanel.getPanel().add(generateInvalidInput.getLabel());
        mainPanel.getPanel().add(generateMustBePositive.getLabel());
        mainPanel.getPanel().add(generateLessThanAdded.getLabel());
        mainPanel.getPanel().add(generateCannotSetOutcome.getLabel());
    }

    private void offLabels() {
        generateSuccess.getLabel().setVisible(false);
        generateInvalidInput.getLabel().setVisible(false);
        generateMustBePositive.getLabel().setVisible(false);
        generateLessThanAdded.getLabel().setVisible(false);
        generateCannotSetOutcome.getLabel().setVisible(false);
    }

    private void callBack() {
        mainPanel.numOfOutcomesField.getTextField().setText(String.valueOf(mainPanel.numberOfOutcomes));
        mainPanel.getPanel().requestFocusInWindow();
    }


    public void setButtonAction() {
        offLabels();

        try {
            if (Integer.parseInt(mainPanel.numOfOutcomesField.getTextField().getText()) < mainPanel.addedOutcomes) {
                generateLessThanAdded.getLabel().setVisible(true);
                timer = new Timer(500, this::callBack);
                timer.start();
            }
            else if (Integer.parseInt(mainPanel.numOfOutcomesField.getTextField().getText()) <= 0) {
                generateMustBePositive.getLabel().setVisible(true);
            }
            else if (Integer.parseInt(mainPanel.numOfOutcomesField.getTextField().getText()) > 1) {
                if (!mainPanel.stack.empty()) {
                    if (mainPanel.stack.peek().probability == 1) {
                        generateCannotSetOutcome.getLabel().setVisible(true);
                        timer = new Timer(500, this::callBack);
                        timer.start();
                    }
                    else {
                        mainPanel.numberOfOutcomes = Integer.parseInt(mainPanel.numOfOutcomesField.getTextField().getText());
                        mainPanel.addButton.updatedAddedOutcomes();
                        mainPanel.addButton.generateAddedOutcomes.getLabel().setVisible(true);
                        generateSuccess.getLabel().setVisible(true);
                    }
                }
                else {
                    mainPanel.numberOfOutcomes = Integer.parseInt(mainPanel.numOfOutcomesField.getTextField().getText());
                    mainPanel.addButton.updatedAddedOutcomes();
                    mainPanel.addButton.generateAddedOutcomes.getLabel().setVisible(true);
                    generateSuccess.getLabel().setVisible(true);
                }
            }
            else {
                mainPanel.numberOfOutcomes = Integer.parseInt(mainPanel.numOfOutcomesField.getTextField().getText());
                mainPanel.addButton.updatedAddedOutcomes();
                mainPanel.addButton.generateAddedOutcomes.getLabel().setVisible(true);
                generateSuccess.getLabel().setVisible(true);
            }
        }
        catch(NumberFormatException e) {
            generateInvalidInput.getLabel().setVisible(true);
        }

        timer = new Timer(3000, this::offLabels);
        timer.start();
    }


    @Override
    public ImagePanel getPanel() {return this.setButton;}
    public void passMainPanel(MainPanel mainPanel) {this.mainPanel = mainPanel;}
}

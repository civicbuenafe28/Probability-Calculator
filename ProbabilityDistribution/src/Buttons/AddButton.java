package Buttons;

import DataHandler.Data;
import Listeners.EnterActionListener;
import Listeners.MouseActionListener;
import Listeners.MouseHoverListener;
import Measurements.*;
import Panels.*;
import Panels.Panel;
import Text.MessageGenerator;
import Text.TextField;
import Timer.Timer;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AddButton implements Panel, MouseActionListener, EnterActionListener {

    public ImagePanel addButton = new ImagePanel("../resources/addButton.png");
    private MouseHoverListener mouseHoverListener = new MouseHoverListener(this.addButton);

    MainPanel mainPanel;

    MessageGenerator setNumOfOutcomesFirst = new MessageGenerator();
    MessageGenerator generateSuccess = new MessageGenerator();
    MessageGenerator generateInvalidOutcomeInput = new MessageGenerator();
    MessageGenerator generateInvalidProbInput = new MessageGenerator();
    MessageGenerator generateProbNotZero = new MessageGenerator();
    MessageGenerator generateGreaterEqualToOne = new MessageGenerator();
    MessageGenerator generateAddedOutcomes = new MessageGenerator();
    MessageGenerator generateMaxOutcomesAdded = new MessageGenerator();
    MessageGenerator generateProbOutcomeOne = new MessageGenerator();

    Timer timer;

    public final int VALID = 0;
    public final int INVALID = -1;
    int[] fieldStates = {INVALID, INVALID};


    public AddButton() {
        addButton.setBounds(Pos.ADD_BUTTON_X, Pos.ADD_BUTTON_Y, Scale.SET_ADD_W, Scale.SET_ADD_H);

        mouseHoverListener.addOldValues(Pos.ADD_BUTTON_X, Pos.ADD_BUTTON_Y, Scale.SET_ADD_W, Scale.SET_ADD_H);
        mouseHoverListener.addNewValues(Pos.ADD_BUTTON_X - 2, Pos.ADD_BUTTON_Y - 2, Scale.SET_ADD_W + 4, Scale.SET_ADD_H + 4);
        mouseHoverListener.addOnHoverAction();
    }

    @Override
    public void addMouseActionListener() {
        addButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                addButton.setBounds(Pos.ADD_BUTTON_X, Pos.ADD_BUTTON_Y, Scale.SET_ADD_W, Scale.SET_ADD_H);
                addButtonAction();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                addButton.setBounds(Pos.ADD_BUTTON_X - 2, Pos.ADD_BUTTON_Y - 2, Scale.SET_ADD_W + 4, Scale.SET_ADD_H + 4);
            }
        });
    }


    @Override
    public void addEnterActionListener(TextField textField) {
        textField.getTextField().addActionListener(e -> addButtonAction());
    }


    public void addLabels() {
        int x = 105, y = 427, w = 250, h = 35;

        setNumOfOutcomesFirst.generate("Set number of outcomes first!", Color.RED, Color.WHITE);
        setNumOfOutcomesFirst.getLabel().setBounds(x, y - 3, w, h);

        generateSuccess.generate("<html>Successfully added <br>outcome & probability!<br></html>", Color.GREEN, Color.WHITE);
        generateSuccess.getLabel().setBounds(x, y - 10, w, h);

        generateInvalidOutcomeInput.generate("Invalid outcome input!", Color.RED, Color.WHITE);
        generateInvalidOutcomeInput.getLabel().setBounds(x, y - 5, w, h);

        generateInvalidProbInput.generate("Invalid probability input!", Color.RED, Color.WHITE);
        generateInvalidProbInput.getLabel().setBounds(x, y - 5, w, h);

        generateProbNotZero.generate("Probability should not be 0!", Color.RED, Color.WHITE);
        generateProbNotZero.getLabel().setBounds(x, y, w, h);

        generateGreaterEqualToOne.generate("<html>Probability must not be greater<br>than or equal to 1!</html>", Color.RED, Color.WHITE);
        generateGreaterEqualToOne.getLabel().setBounds(x, y - 10, w, h);

        generateAddedOutcomes.setFontSize(16);
        generateAddedOutcomes.generate(
                "Added " + mainPanel.addedOutcomes + " outcomes out of " + mainPanel.numberOfOutcomes,
                Color.WHITE, Color.decode("#940000")
        );
        generateAddedOutcomes.getLabel().setBounds(516, 211, 250, 15);

        generateMaxOutcomesAdded.generate("<html>Max number<br>of outcomes added!</html>", Color.RED, Color.WHITE);
        generateMaxOutcomesAdded.getLabel().setBounds(x, y - 10, w, h);

        generateProbOutcomeOne.getLabel().setBounds(x, y - 10, w, h);
        generateProbOutcomeOne.generate("<html>Proabability of 1 only allowed<br>if no. of outcomes is also 1</html>", Color.RED, Color.WHITE);


        mainPanel.getPanel().add(setNumOfOutcomesFirst.getLabel());
        mainPanel.getPanel().add(generateSuccess.getLabel());
        mainPanel.getPanel().add(generateInvalidOutcomeInput.getLabel());
        mainPanel.getPanel().add(generateInvalidProbInput.getLabel());
        mainPanel.getPanel().add(generateProbNotZero.getLabel());
        mainPanel.getPanel().add(generateGreaterEqualToOne.getLabel());
        mainPanel.getPanel().add(generateAddedOutcomes.getLabel());
        mainPanel.getPanel().add(generateMaxOutcomesAdded.getLabel());
        mainPanel.getPanel().add(generateProbOutcomeOne.getLabel());
    }


    private void offLabels() {
        generateInvalidProbInput.getLabel().setVisible(false);
        setNumOfOutcomesFirst.getLabel().setVisible(false);
        generateSuccess.getLabel().setVisible(false);
        generateInvalidOutcomeInput.getLabel().setVisible(false);
        generateInvalidOutcomeInput.getLabel().setVisible(false);
        generateProbNotZero.getLabel().setVisible(false);
        generateGreaterEqualToOne.getLabel().setVisible(false);
        generateMaxOutcomesAdded.getLabel().setVisible(false);
        generateProbOutcomeOne.getLabel().setVisible(false);
    }


    public void updatedAddedOutcomes() {
        generateAddedOutcomes.generate(
                "Added " + mainPanel.addedOutcomes + " outcomes out of " + mainPanel.numberOfOutcomes,
                Color.WHITE, Color.decode("#940000")
        );
    }


    public void addButtonAction() {
        offLabels();

        Data data;
        int outcomeInput;
        double probInput = mainPanel.EMPTY;

        if (mainPanel.numberOfOutcomes == mainPanel.EMPTY) {
            setNumOfOutcomesFirst.getLabel().setVisible(true);
            timer = new Timer(2300, this::offLabels);
            timer.start();

            fieldStates[0] = INVALID;
            fieldStates[1] = INVALID;

            return;
        }

        if (mainPanel.addedOutcomes == mainPanel.numberOfOutcomes) {
            generateMaxOutcomesAdded.getLabel().setVisible(true);
            timer = new Timer(2300, this::offLabels);
            timer.start();

            fieldStates[0] = INVALID;
            fieldStates[1] = INVALID;

            return;
        }


        try {
            outcomeInput = Integer.parseInt(mainPanel.outcomesField.getTextField().getText());
            fieldStates[0] = VALID;
        }
        catch(NumberFormatException e) {
            generateInvalidOutcomeInput.getLabel().setVisible(true);
            generateAddedOutcomes.getLabel().setVisible(true);
            timer = new Timer(2300, this::offLabels);
            timer.start();

            fieldStates[0] = INVALID;
            fieldStates[1] = INVALID;

            return;
        }


        try {
            probInput = Double.parseDouble(mainPanel.probabilityField.getTextField().getText());

            if (probInput == 1) {
                if (mainPanel.numberOfOutcomes == 1) {
                    fieldStates[1] = VALID;
                }
                else {
                    generateProbOutcomeOne.getLabel().setVisible(true);
                    timer = new Timer(3500, this::offLabels);
                    timer.start();

                    fieldStates[0] = INVALID;
                    fieldStates[1] = INVALID;

                    return;
                }
            }
            else if (probInput >= 1 && mainPanel.numberOfOutcomes >= 1) {
                generateGreaterEqualToOne.getLabel().setVisible(true);
                timer = new Timer(2300, this::offLabels);
                timer.start();

                fieldStates[0] = INVALID;
                fieldStates[1] = INVALID;

                return;
            }

            if (probInput == 0) {
                generateProbNotZero.getLabel().setVisible(true);
                timer = new Timer(2300, this::offLabels);
                timer.start();

                fieldStates[0] = INVALID;
                fieldStates[1] = INVALID;

                return;
            }
            else {
                fieldStates[1] = VALID;
            }
        }
        catch(NumberFormatException e) {
            generateInvalidProbInput.getLabel().setVisible(true);
        }


        if (fieldStates[0] == VALID && fieldStates[1] == VALID) {
            data = new Data(outcomeInput, probInput);
            mainPanel.stack.add(data);

            mainPanel.outcomeProbabilityField.reprintTextArea();
            mainPanel.addedOutcomes++;
            updatedAddedOutcomes();


            generateAddedOutcomes.getLabel().setVisible(true);
            generateSuccess.getLabel().setVisible(true);
        }

        fieldStates[0] = INVALID;
        fieldStates[1] = INVALID;

        timer = new Timer(2300, this::offLabels);
        timer.start();
    }


    @Override
    public ImagePanel getPanel() {
        return this.addButton;
    }
    public void passMainPanel(MainPanel mainPanel) {this.mainPanel = mainPanel;}
}

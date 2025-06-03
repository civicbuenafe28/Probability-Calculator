package Buttons;

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

public class UndoButton implements Panel, MouseActionListener {

    public ImagePanel undoButton = new ImagePanel("../resources/undoButton.png");
    private MouseHoverListener mouseHoverListener = new MouseHoverListener(undoButton);
    MessageGenerator generateStackEmpty = new MessageGenerator();
    MessageGenerator generateUndoSuccessful = new MessageGenerator();

    MainPanel mainPanel;

    Timer timer;

    public UndoButton() {
        undoButton.setBounds(Pos.UNDO_BUTTON_X, Pos.UNDO_BUTTON_Y, Scale.CONTROL_BUTTON_W, Scale.CONTROL_BUTTON_H);

        mouseHoverListener.addOldValues(Pos.UNDO_BUTTON_X, Pos.UNDO_BUTTON_Y, Scale.CONTROL_BUTTON_W, Scale.CONTROL_BUTTON_H);
        mouseHoverListener.addNewValues(Pos.UNDO_BUTTON_X - 2, Pos.UNDO_BUTTON_Y - 2, Scale.CONTROL_BUTTON_W + 4, Scale.CONTROL_BUTTON_H + 4);
        mouseHoverListener.addOnHoverAction();
    }



    @Override
    public void addMouseActionListener() {
        undoButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                undoButton.setBounds(Pos.UNDO_BUTTON_X, Pos.UNDO_BUTTON_Y, Scale.CONTROL_BUTTON_W, Scale.CONTROL_BUTTON_H);
                undoButtonAction();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                undoButton.setBounds(Pos.UNDO_BUTTON_X - 2, Pos.UNDO_BUTTON_Y - 2, Scale.CONTROL_BUTTON_W + 4, Scale.CONTROL_BUTTON_H + 4);
            }
        });
    }


    public void addLabels() {
        int x = 210, y = 506, w = 150, h = 15;

        generateStackEmpty.generate("Nothing left to undo!", Color.RED, Color.decode("#efe8e8"));
        generateStackEmpty.setFontSize(15);
        generateStackEmpty.getLabel().setBounds(x, y, w, h);

        generateUndoSuccessful.generate("Undo successful!", Color.GREEN, Color.decode("#efe8e8"));
        generateUndoSuccessful.setFontSize(15);
        generateUndoSuccessful.getLabel().setBounds(x, y, w, h);


        mainPanel.getPanel().add(generateStackEmpty.getLabel());
        mainPanel.getPanel().add(generateUndoSuccessful.getLabel());
    }


    public void offLabels() {
        generateStackEmpty.getLabel().setVisible(false);
        generateUndoSuccessful.getLabel().setVisible(false);
    }


    public void undoButtonAction() {
        mainPanel.calculateButton.offLabels();
        offLabels();

        if (mainPanel.stack.empty()) {
            generateStackEmpty.getLabel().setVisible(true);

            timer = new Timer(2300, this::offLabels);
            timer.start();
        }
        else {
            mainPanel.stack.pop();
            mainPanel.addedOutcomes--;
            mainPanel.addButton.updatedAddedOutcomes();

            mainPanel.addButton.generateAddedOutcomes.getLabel().setVisible(true);
            mainPanel.outcomeProbabilityField.reprintTextArea();

            generateUndoSuccessful.getLabel().setVisible(true);

            timer = new Timer(2300, this::offLabels);
            timer.start();
        }
    }


    @Override
    public ImagePanel getPanel() {return this.undoButton;}
    public void passMainPanel(MainPanel mainPanel) {this.mainPanel = mainPanel;}
}

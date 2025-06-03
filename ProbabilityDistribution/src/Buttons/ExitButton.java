package Buttons;

import Listeners.MouseActionListener;
import Listeners.MouseHoverListener;
import Measurements.Pos;
import Measurements.Scale;
import Panels.ImagePanel;
import Panels.Panel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ExitButton implements Panel, MouseActionListener {

    public ImagePanel exitButton = new ImagePanel("../resources/exitButton.png");
    private MouseHoverListener mouseHoverListener = new MouseHoverListener(exitButton);

    public ExitButton() {
        exitButton.setBounds(Pos.EXIT_BUTTON_X, Pos.EXIT_BUTTON_Y, Scale.CONTROL_BUTTON_W, Scale.CONTROL_BUTTON_H);
        exitButton.setLayout(null);

        mouseHoverListener.addOldValues(Pos.EXIT_BUTTON_X, Pos.EXIT_BUTTON_Y, Scale.CONTROL_BUTTON_W, Scale.CONTROL_BUTTON_H);
        mouseHoverListener.addNewValues(Pos.EXIT_BUTTON_X - 2, Pos.EXIT_BUTTON_Y - 2, Scale.CONTROL_BUTTON_W + 4, Scale.CONTROL_BUTTON_H + 4);
        mouseHoverListener.addOnHoverAction();

        addMouseActionListener();
    }

    @Override
    public void addMouseActionListener() {
        exitButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                exitButton.setBounds(Pos.EXIT_BUTTON_X, Pos.EXIT_BUTTON_Y, Scale.CONTROL_BUTTON_W, Scale.CONTROL_BUTTON_H);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                exitButton.setBounds(Pos.EXIT_BUTTON_X - 2, Pos.EXIT_BUTTON_Y - 2, Scale.CONTROL_BUTTON_W + 4, Scale.CONTROL_BUTTON_H + 4);

                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }

                System.exit(0);
            }
        });
    }


    @Override
    public ImagePanel getPanel() {
        return this.exitButton;
    }
}

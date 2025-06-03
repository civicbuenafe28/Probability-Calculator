package Panels;

import Listeners.MouseActionListener;
import Listeners.MouseHoverListener;
import Measurements.Pos;
import Measurements.Scale;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class WelcomePanel implements Panel {

    public ImagePanel welcomePanel = new ImagePanel();
    public ImagePanel logo = new ImagePanel("../resources/logo_white.png");
    public ImagePanel welcomeText = new ImagePanel("../resources/welcomeText.png");
    public ImagePanel descriptionText = new ImagePanel("../resources/descriptionText.png");
    public ImagePanel startButton = new ImagePanel("../resources/startButton.png");

    public boolean startButtonPressed = false;


    public WelcomePanel() {
        welcomePanel.setLayout(null);
        welcomePanel.setBounds(Pos.WELCOME_PANEL_X, Pos.WELCOME_PANEL_Y, Scale.WELCOME_PANEL_W, Scale.WELCOME_PANEL_H);
        welcomePanel.setBackground(Color.decode("#940000"));

        logo.setBounds(Pos.LOGO_WHITE_X, Pos.LOGO_WHITE_Y - 40, Scale.LOGO_WHITE_W, Scale.LOGO_WHITE_H);
        MouseHoverListener logoHover = new MouseHoverListener(logo);
        logoHover.addOldValues(Pos.LOGO_WHITE_X, Pos.LOGO_WHITE_Y - 40, Scale.LOGO_WHITE_W, Scale.LOGO_WHITE_H);
        logoHover.addNewValues(Pos.LOGO_WHITE_X - 4, (Pos.LOGO_WHITE_Y - 40) - 4, Scale.LOGO_WHITE_W + 8, Scale.LOGO_WHITE_H + 8);
        logoHover.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        logoHover.addOnHoverAction();
        welcomePanel.add(logo);

        welcomeText.setBounds(350, 165, 260, 30);
        welcomePanel.add(welcomeText);

        descriptionText.setBounds(200, 505, 600, 20);
        welcomePanel.add(descriptionText);

        startButton.setBounds(423, 565, 125, 38);
        MouseHoverListener startButtonHover = new MouseHoverListener(startButton);
        startButtonHover.addOldValues(423, 565, 125, 38);
        startButtonHover.addNewValues(423 - 3, 565 - 2, 125 + 6, 38 + 4);
        startButtonHover.addOnHoverAction();

        startButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                startButtonPressed = true;
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                startButton.setBounds(423 - 2, 565 - 2, 125 + 4, 38 + 4);
            }
        });


        welcomePanel.add(startButton);
    }

    public boolean isStartButtonPressed() {
        return startButtonPressed;
    }

    public ImagePanel getStartButton() {
        return startButton;
    }

    @Override
    public ImagePanel getPanel() {
        return welcomePanel;
    }
}

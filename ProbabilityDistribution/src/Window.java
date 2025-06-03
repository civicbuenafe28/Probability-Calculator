import Measurements.Scale;
import Panels.MainPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Window {

    JFrame window = new JFrame();

    public Window() {
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setSize(Scale.WINDOW_W, Scale.WINDOW_H);
        window.setLayout(null);
        window.setLocationRelativeTo(null);
        window.setTitle("Probability Distribution Calculator");
        window.setFocusable(true);
        window.requestFocusInWindow();

        Image icon = new ImageIcon(getClass().getResource("resources/icon.png")).getImage();
        window.setIconImage(icon);

        window.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                window.requestFocusInWindow();
            }
        });
    }

    public JFrame getWindow() {return this.window;}
}

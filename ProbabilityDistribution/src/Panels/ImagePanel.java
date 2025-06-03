package Panels;

import javax.swing.*;
import java.awt.*;

public class ImagePanel extends JPanel {

    private Image backgroundImage;

    public ImagePanel() {}

    public ImagePanel(String imagePath) {
        backgroundImage = new ImageIcon(getClass().getResource(imagePath)).getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }

    public void setImage(String imagePath) {
        backgroundImage = new ImageIcon(getClass().getResource(imagePath)).getImage();
        repaint();
    }
}

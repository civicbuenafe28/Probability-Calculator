package Listeners;

import Panels.ImagePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseHoverListener extends JFrame {

    ImagePanel panel;

    int[] oldValues = new int[4];
    int[] newValues = new int[4];
    Cursor cursor = new Cursor(Cursor.HAND_CURSOR);

    public MouseHoverListener(ImagePanel panel) {
        this.panel = panel;
    }

    public void addOldValues(int oldX, int oldY, int oldW, int oldH) {
        this.oldValues[0] = oldX;
        this.oldValues[1] = oldY;
        this.oldValues[2] = oldW;
        this.oldValues[3] = oldH;
    }

    public void addNewValues(int newX, int newY, int newW, int newH) {
        this.newValues[0] = newX;
        this.newValues[1] = newY;
        this.newValues[2] = newW;
        this.newValues[3] = newH;
    }

    public void addOnHoverAction() {
        this.panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                panel.setBounds(newValues[0], newValues[1], newValues[2], newValues[3]);
                panel.setCursor(cursor);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                panel.setBounds(oldValues[0], oldValues[1], oldValues[2], oldValues[3]);
                panel.setCursor(cursor);
            }
        });
    }

    public void setCursor(Cursor cursor) {
        this.cursor = cursor;
    }
}

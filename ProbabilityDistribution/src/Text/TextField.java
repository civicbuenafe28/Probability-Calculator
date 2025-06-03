package Text;

import Listeners.MouseActionListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TextField implements MouseActionListener {

    public JTextField textField = new JTextField(100);
    String initialMessage;

    public TextField(String initialMessage) {
        this.initialMessage = initialMessage;
        textField.setText(initialMessage);
        textField.setEditable(true);

        textField.setBackground(Color.decode("#f1f0f0"));
        textField.setForeground(Color.decode("#a3a3a3"));
        textField.setCaretColor(Color.GRAY);
        textField.setFont(new Font("Dialog", Font.PLAIN, 16));
        textField.setBorder(BorderFactory.createEmptyBorder());
    }

    @Override
    public void addMouseActionListener() {
        textField.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (textField.getText().equalsIgnoreCase(initialMessage)) {
                    textField.setText("");
                    textField.setForeground(Color.decode("#800404"));
                }
            }
        });

        textField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (textField.getText().equalsIgnoreCase(initialMessage)) {
                    textField.setForeground(Color.decode("#800404"));
                    textField.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (textField.getText().isBlank()) {
                    textField.setForeground(Color.decode("#a3a3a3"));
                    textField.setText(initialMessage);
                }
            }
        });
    }


    public JTextField getTextField() {return textField;}
}

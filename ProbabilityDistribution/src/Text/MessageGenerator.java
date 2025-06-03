package Text;

import javax.swing.*;
import java.awt.*;

public class MessageGenerator {

    JLabel label = new JLabel();
    int fontSize = 14;

    public void generate(String message, Color foregroundColor, Color backgroundColor) {
        label.setText(message);
        label.setForeground(foregroundColor);
        label.setBackground(backgroundColor);
        label.setFont(new Font("Dialog", Font.PLAIN, fontSize));
        label.setFocusable(false);

        label.setVisible(false);
    }

    public void setFontSize(int fontSize) {this.fontSize = fontSize;}

    public JLabel getLabel() {return label;}
}

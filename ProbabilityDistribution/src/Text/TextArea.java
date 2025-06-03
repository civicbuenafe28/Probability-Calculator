package Text;

import DataHandler.Data;
import Panels.MainPanel;

import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;

public class TextArea {

    JTextArea outcomesTextArea = new JTextArea(1, 100);
    JScrollPane scrollPane;

    MainPanel mainPanel;

    public TextArea(MainPanel mainPanel) {
        this.mainPanel = mainPanel;

        outcomesTextArea.setText(mainPanel.result);
        outcomesTextArea.setBackground(Color.decode("#800404"));
        outcomesTextArea.setForeground(Color.WHITE);
        outcomesTextArea.setBounds(527, 244, 383, 184);
        outcomesTextArea.setBorder(BorderFactory.createEmptyBorder());
        outcomesTextArea.setLineWrap(true);
        outcomesTextArea.setCaretColor(Color.WHITE);
        outcomesTextArea.setFont(new Font("Dialog", Font.PLAIN, 16));
        outcomesTextArea.setEditable(false);

        scrollPane = new JScrollPane(outcomesTextArea);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.setBounds(527, 245, 383, 184);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        JScrollBar vertical = scrollPane.getVerticalScrollBar();
        vertical.setBorder(BorderFactory.createEmptyBorder());

        vertical.setUI(new BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                this.thumbColor = Color.decode("#600505");
                this.trackColor = Color.decode("#800404");
            }
        });
    }


    public void reprintTextArea() {
        mainPanel.outcomeProbabilityField.getTextArea().setText("");
        mainPanel.result = "X\t\tP(X)\n\n";

        StringBuilder builder = new StringBuilder();

        for (Data data : mainPanel.stack) {
            builder.append(data.outcome).append("\t\t").append(data.probability).append("\n");
        }

        mainPanel.result += builder.toString();
        mainPanel.outcomeProbabilityField.getTextArea().setText(mainPanel.result);
    }


    public void popData() {
        mainPanel.stack.pop();
    }



    public JTextArea getTextArea() {return outcomesTextArea;}
    public JScrollPane getScrollPane() {return scrollPane;}
}

package Panels;

import Measurements.Pos;
import Measurements.Scale;

public class OutcomesTextAreaPanel implements Panel {

    ImagePanel outcomesTextAreaPanel = new ImagePanel("../resources/outcomesTextArea.png");

    public OutcomesTextAreaPanel() {
        outcomesTextAreaPanel.setBounds(Pos.OUTCOMES_AREA_X, Pos.OUTCOMES_AREA_Y, Scale.OUTCOMES_AREA_W, Scale.OUTCOMES_AREA_H);
    }

    @Override
    public ImagePanel getPanel() {
        return this.outcomesTextAreaPanel;
    }
}

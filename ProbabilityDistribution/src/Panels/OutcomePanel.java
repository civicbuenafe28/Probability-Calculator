package Panels;

import Measurements.Pos;
import Measurements.Scale;

public class OutcomePanel implements Panel {

    ImagePanel outcomePanel = new ImagePanel("../resources/outcomePanel.png");

    public OutcomePanel() {
        outcomePanel.setBounds(Pos.OUTCOME_PANEL_X, Pos.OUTCOME_PANEL_Y, Scale.OUTCOME_PANEL_W, Scale.OUTCOME_PANEL_H);
        outcomePanel.setLayout(null);
    }

    @Override
    public ImagePanel getPanel() {
        return this.outcomePanel;
    }
}

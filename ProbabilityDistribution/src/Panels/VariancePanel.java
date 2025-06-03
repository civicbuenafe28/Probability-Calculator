package Panels;

import Measurements.Pos;
import Measurements.Scale;

public class VariancePanel implements Panel {

    public ImagePanel variancePanel = new ImagePanel("../resources/variance.png");

    public VariancePanel() {
        variancePanel.setBounds(Pos.VAR_OUTCOME_X, Pos.VAR_OUTCOME_Y, Scale.DISTRIBUTION_W, Scale.DISTRIBUTION_H);
    }

    @Override
    public ImagePanel getPanel() {
        return this.variancePanel;
    }
}

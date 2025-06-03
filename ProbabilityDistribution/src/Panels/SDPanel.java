package Panels;

import Measurements.Pos;
import Measurements.Scale;

public class SDPanel implements Panel {

    public ImagePanel sdPanel = new ImagePanel("../resources/sd.png");

    public SDPanel() {
        sdPanel.setBounds(Pos.SD_OUTCOME_X, Pos.SD_OUTCOME_Y, Scale.DISTRIBUTION_W, Scale.DISTRIBUTION_H);
    }

    @Override
    public ImagePanel getPanel() {
        return this.sdPanel;
    }
}

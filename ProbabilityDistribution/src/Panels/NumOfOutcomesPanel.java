package Panels;

import Measurements.Pos;
import Measurements.Scale;

public class NumOfOutcomesPanel implements Panel {

    ImagePanel numOfOutcomesPanel = new ImagePanel("../resources/numOfOutcomesPanel.png");

    public NumOfOutcomesPanel() {
        numOfOutcomesPanel.setBounds(Pos.NUM_OF_OUTCOMES_X, Pos.NUM_OF_OUTCOMES_Y, Scale.NUM_OF_OUTCOMES_W, Scale.NUM_OF_OUTCOMES_H);
        numOfOutcomesPanel.setLayout(null);
    }

    @Override
    public ImagePanel getPanel() {
        return this.numOfOutcomesPanel;
    }
}

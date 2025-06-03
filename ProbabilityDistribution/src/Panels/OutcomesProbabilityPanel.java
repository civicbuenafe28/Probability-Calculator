package Panels;

import Measurements.Pos;
import Measurements.Scale;

public class OutcomesProbabilityPanel implements Panel {

    ImagePanel outcomesProbability = new ImagePanel("../resources/outcomesAndProbabilityPanel.png");

    public OutcomesProbabilityPanel() {
        outcomesProbability.setBounds(Pos.OUTCOMES_PROB_X, Pos.OUTCOMES_PROB_Y, Scale.OUTCOMES_PROB_W, Scale.OUTCOMES_PROB_H);
    }

    @Override
    public ImagePanel getPanel() {
        return this.outcomesProbability;
    }
}

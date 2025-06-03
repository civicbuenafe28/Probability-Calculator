package Panels;

import Measurements.Pos;
import Measurements.Scale;

import javax.swing.*;

public class MeanPanel implements Panel {

    ImagePanel meanOutcome = new ImagePanel("../resources/mean.png");
    JTextField textField;

    public MeanPanel() {
        meanOutcome.setBounds(Pos.MEAN_OUTCOME_X, Pos.MEAN_OUTCOME_Y, Scale.DISTRIBUTION_W, Scale.DISTRIBUTION_H);
    }

    @Override
    public ImagePanel getPanel() {
        return this.meanOutcome;
    }
}

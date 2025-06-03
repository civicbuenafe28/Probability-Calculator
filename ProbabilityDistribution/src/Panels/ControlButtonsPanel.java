package Panels;

import Measurements.Pos;
import Measurements.Scale;

public class ControlButtonsPanel implements Panel {

    ImagePanel controlButtonsPanel = new ImagePanel("../resources/controlButtonsPanel.png");

    public ControlButtonsPanel() {
        controlButtonsPanel.setBounds(Pos.CONTROL_PANEL_X, Pos.CONTROL_PANEL_Y, Scale.CONTROL_PANEL_W, Scale.CONTROL_PANEL_H);
        controlButtonsPanel.setLayout(null);
    }

    @Override
    public ImagePanel getPanel() {
        return this.controlButtonsPanel;
    }
}

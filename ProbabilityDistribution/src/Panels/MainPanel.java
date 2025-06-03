package Panels;

import Buttons.*;
import DataHandler.Data;
import Listeners.MouseHoverListener;
import Measurements.Pos;
import Measurements.Scale;
import Text.TextField;
import Text.TextArea;

import java.util.Stack;


public class MainPanel implements Panel {

    public ImagePanel mainPanel = new ImagePanel("../resources/mainPanel.png");
    public ImagePanel logo = new ImagePanel("../resources/logo.png");
    ImagePanel title = new ImagePanel("../resources/title.png");

    public TextField numOfOutcomesField = new TextField("Set no. of outcomes");
    public TextField outcomesField = new TextField("Enter X");
    public TextField probabilityField = new TextField("Enter probability");

    public TextField meanTextField = new TextField("Mean");
    public TextField varTextField = new TextField("Variance");
    public TextField sdTextField = new TextField("Standard Deviation");

    public TextArea outcomeProbabilityField = new TextArea(this);

    public OutcomesTextAreaPanel outcomesTextAreaPanel = new OutcomesTextAreaPanel();
    public OutcomePanel outcomePanel = new OutcomePanel();
    public AddButton addButton = new AddButton();
    public SetButton setButton = new SetButton();
    public NumOfOutcomesPanel numOfOutcomesPanel = new NumOfOutcomesPanel();
    public OutcomesProbabilityPanel outcomesProbabilityPanel = new OutcomesProbabilityPanel();

    public MeanPanel meanPanel = new MeanPanel();
    public VariancePanel variancePanel = new VariancePanel();
    public SDPanel sdPanel = new SDPanel();

    public CalculateButton calculateButton = new CalculateButton();
    public UndoButton undoButton = new UndoButton();
    public ClearButton clearButton = new ClearButton();
    public ExitButton exitButton = new ExitButton();
    public ControlButtonsPanel controlButtonsPanel = new ControlButtonsPanel();

    public int EMPTY = 0;
    public int addedOutcomes = 0;
    public int numberOfOutcomes = EMPTY;
    public String result = "X\t\tP(X)\n\n";

    public Stack<Data> stack = new Stack<>();

    public MainPanel() {
        mainPanel.setBounds(Pos.MAIN_PANEL_X, Pos.MAIN_PANEL_Y, Scale.MAIN_PANEL_W, Scale.MAIN_PANEL_H);
        mainPanel.setLayout(null);


        logo.setBounds(Pos.LOGO_X, Pos.LOGO_Y, Scale.LOGO_W, Scale.LOGO_H);
        MouseHoverListener logoHoverListener = new MouseHoverListener(logo);
        logoHoverListener.addOldValues(Pos.LOGO_X, Pos.LOGO_Y, Scale.LOGO_W, Scale.LOGO_H);
        logoHoverListener.addNewValues(Pos.LOGO_X - 2, Pos.LOGO_Y - 2, Scale.LOGO_W + 4, Scale.LOGO_H + 4);
        logoHoverListener.addOnHoverAction();
        mainPanel.add(logo);

        title.setBounds(Pos.TITLE_X, Pos.TITLE_Y, Scale.TITLE_W, Scale.TITLE_H);
        mainPanel.add(title);


        // Set text field positions and sizes and add to main panel
        numOfOutcomesField.getTextField().setBounds(107, 184, 310, 22);
        numOfOutcomesField.addMouseActionListener();
        mainPanel.add(numOfOutcomesField.getTextField());

        outcomesField.getTextField().setBounds(107, 336, 310, 22);
        outcomesField.addMouseActionListener();
        mainPanel.add(outcomesField.getTextField());

        probabilityField.getTextField().setBounds(107, 383, 310, 22);
        probabilityField.addMouseActionListener();
        mainPanel.add(probabilityField.getTextField());


        // Set and add result text fields
        meanTextField.getTextField().setBounds(681, 531, 235, 22);
        meanTextField.getTextField().setEditable(false);
        meanTextField.getTextField().setFocusable(false);
        mainPanel.add(meanTextField.getTextField());

        varTextField.getTextField().setBounds(681, 606, 235, 22);
        varTextField.getTextField().setEditable(false);
        varTextField.getTextField().setFocusable(false);
        mainPanel.add(varTextField.getTextField());

        sdTextField.getTextField().setBounds(681, 680, 235, 22);
        sdTextField.getTextField().setEditable(false);
        sdTextField.getTextField().setFocusable(false);
        mainPanel.add(sdTextField.getTextField());


        // Add Outcomes-Probability text area
        mainPanel.add(outcomeProbabilityField.getScrollPane());
        outcomeProbabilityField.getTextArea().setText(result);


        // Set and add Buttons
        setButton.passMainPanel(this);
        setButton.addMouseActionListener();
        setButton.addEnterActionListener(numOfOutcomesField);
        setButton.addLabels();
        mainPanel.add(setButton.getPanel());

        addButton.passMainPanel(this);
        addButton.addMouseActionListener();
        addButton.addEnterActionListener(outcomesField);
        addButton.addEnterActionListener(probabilityField);
        addButton.addLabels();
        mainPanel.add(addButton.getPanel());

        calculateButton.passMainPanel(this);
        calculateButton.addMouseActionListener();
        calculateButton.addLabels();
        mainPanel.add(calculateButton.getPanel());

        undoButton.passMainPanel(this);
        undoButton.addMouseActionListener();
        undoButton.addLabels();
        mainPanel.add(undoButton.getPanel());

        clearButton.passMainPanel(this);
        clearButton.addMouseActionListener();
        clearButton.addLabels();
        mainPanel.add(clearButton.getPanel());

        mainPanel.add(exitButton.getPanel());


        // Add Panels
        mainPanel.add(controlButtonsPanel.getPanel());
        mainPanel.add(outcomesTextAreaPanel.getPanel());
        mainPanel.add(outcomePanel.getPanel());
        mainPanel.add(numOfOutcomesPanel.getPanel());
        mainPanel.add(outcomesProbabilityPanel.getPanel());
        mainPanel.add(meanPanel.getPanel());
        mainPanel.add(variancePanel.getPanel());
        mainPanel.add(sdPanel.getPanel());
    }



    @Override
    public ImagePanel getPanel() {return this.mainPanel;}
}
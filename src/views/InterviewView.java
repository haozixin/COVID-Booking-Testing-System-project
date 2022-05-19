package views;

import models.CovidTestModel;
import models.UserModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Objects;

public class InterviewView extends View {
    public static final String TAG1 = "Interview";
    public static final String TAG2 = "Create COVID-Test";
    public static final String TAG3 = "View bookings of the user";
    public static final String EMPTY_OPTION = "--select one--";
    private UserModel theModel;
    private CovidTestModel covidTestModel;

    private JPanel panel1 = new JPanel();
    private JTabbedPane tabbedPanel = new JTabbedPane();
    private JScrollPane jp = new JScrollPane();
    private JLabel symptomLabel = new JLabel("Please select the customer's symptoms here:");
    private JCheckBox fluCheckBox = new JCheckBox("Does the customer have a flu?");
    private JCheckBox coughCheckBox = new JCheckBox("Does the customer have a cough?");
    private JCheckBox feverCheckBox = new JCheckBox("Does the customer have a fever?");
    private JCheckBox headacheCheckBox = new JCheckBox("Does the customer have a headache?");
    private JCheckBox chestPainCheckBox = new JCheckBox("Does the customer have a chest pain?");
    private JCheckBox diarrhoeaCheckBox = new JCheckBox("Does the customer have a diarrhoea?");
    private JLabel decisionLabel = new JLabel("Referencing the suggestion from the system, please select your final decision here:");
    private JComboBox cmb = new JComboBox();
    private JButton button = new JButton("Get suggestions");

    private JLabel userNameLabel = new JLabel("Enter userName:");
    private JTextField userNameTextField = new JTextField(30);
    private JTextArea bookings = new JTextArea("Here is the place to showing the bookings information of patients\n" +
            "please go to \"Create COVID-test window\" window and finish the rest process");
    private JLabel bookingIdLabel = new JLabel("Enter the chosen bookingId:");
    private JTextField bookingIdTextField = new JTextField(30);
    private JButton button2 = new JButton("Get existing bookings information");
    private JButton button3 = new JButton("Submit");


    public InterviewView(UserModel theModel, CovidTestModel covidTestModel) {
        super("On-site Testing Subsystem - Interview operation");

        this.theModel = theModel;
        this.covidTestModel = covidTestModel;
        GridBagConstraints constraints = setBasicStyle(panel);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(800, 600);


        addComponentsToPanel(panel, constraints, symptomLabel);
        addComponentsToPanel(panel, constraints, fluCheckBox);
        addComponentsToPanel(panel, constraints, coughCheckBox);
        addComponentsToPanel(panel, constraints, feverCheckBox);
        addComponentsToPanel(panel, constraints, headacheCheckBox);
        addComponentsToPanel(panel, constraints, chestPainCheckBox);
        addComponentsToPanel(panel, constraints, diarrhoeaCheckBox);
        addComponentsToPanel(panel, constraints, decisionLabel);



        cmb.addItem(EMPTY_OPTION);
        cmb.addItem("PCR");
        cmb.addItem("RAT");


        GridBagConstraints constraints2 = setBasicStyle(panel1);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(800, 600);

        resetGrids();

        addComponentsToPanel(panel1, constraints2, userNameLabel);
        addComponentsToPanel(panel1, constraints2, userNameTextField);
        addComponentsToPanel(panel1, constraints2, state);
        addComponentsToPanel(panel1, constraints2, decisionLabel);
        addComponentsToPanel(panel1, constraints2, cmb);
        addComponentsToPanel(panel1, constraints2, new JLabel());
        addComponentsToPanel(panel1, constraints2, bookingIdLabel);
        addComponentsToPanel(panel1, constraints2, button2);
        addComponentsToPanel(panel1, constraints2, bookingIdTextField);
        addComponentsToPanel(panel1, constraints2, button3);


        jp.setLayout(new ScrollPaneLayout());
        bookings.setEditable(false);
        jp.setViewportView(bookings);


        tabbedPanel.addTab(TAG1, panel);
        tabbedPanel.addTab(TAG3, jp);
        tabbedPanel.addTab(TAG2, panel1);


        this.add(tabbedPanel);
    }


    public String getUserNameTextField() {
        return userNameTextField.getText();
    }

    public String getCmb() {
        return Objects.requireNonNull(cmb.getSelectedItem()).toString();
    }

    public String getBookingId() {
        return bookingIdTextField.getText();
    }

    @Override
    public void update() {


        if (theModel.getBookings() != null) {
            bookings.setForeground(new Color(11, 75, 5));
            bookings.setText(theModel.getBookings());
            state.setForeground(new Color(11, 75, 5));
            state.setText("Bookings information are available now in the " + TAG3 + " tab");
        } else {
            bookings.setForeground(new Color(100, 2, 2));
            bookings.setText("No bookings found for this customer");
            state.setForeground(new Color(100, 2, 2));
            state.setText("No bookings found for this customer");
        }

        // if creating covid-test is done (when there is no error message)
        if (covidTestModel.getResponseMessage().equals("")) {

            panel.removeAll();
            state.setText("Have create a Covid-test! (you could close the window now)");


            panel1.removeAll();
            panel1.add(state);

            bookings.setText(state.getText());
            panel.update(panel.getGraphics());
            panel1.update(panel1.getGraphics());
        } else {
            JOptionPane.showMessageDialog(this, covidTestModel.getResponseMessage());
        }
    }

    @Override
    public void addButtonListener(ActionListener listener) {
        button.addActionListener(listener);
    }

    public void addButton2Listener(ActionListener listener) {
        button2.addActionListener(listener);
    }

    public void addButton3Listener(ActionListener listener) {
        button3.addActionListener(listener);
    }

    public int countCheckBox() {

        Component[] components = panel.getComponents();
        int counter = 0;
        for (Component component : components) {
            if (component instanceof JCheckBox) {
                JCheckBox jcb = (JCheckBox) component;
                if (jcb.isSelected()) {
                    counter++;
                }
            }
        }
        return counter;
    }


}

package views;

import models.CovidTest;
import models.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Objects;

public class InterviewView extends View {
    public static final String TAG1 = "Interview";
    public static final String TAG2 = "Create COVID-Test";
    public static final String TAG3 = "View bookings of the user";
    public static final String EMPTY_OPTION = "--select one--";
    private User theModel;
    private CovidTest covidTestModel;

    private JPanel panel1=new JPanel();
    private JTabbedPane tabbedPanel=new JTabbedPane();
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
    private JTextArea bookings = new JTextArea("Here is the place to showing the bookings information of patients\n"+
            "please go to \"Create COVID-test window\" window and finish the rest process");
    private JLabel bookingIdLabel = new JLabel("Enter the chosen bookingId:");
    private JTextField bookingIdTextField = new JTextField(30);
    private JButton button2 = new JButton("Get existing bookings information");
    private JButton button3 = new JButton("Submit");


    public InterviewView(User theModel, CovidTest covidTestModel) {
        super("On-site Testing Subsystem - Interview operation");

        this.theModel = theModel;
        this.covidTestModel = covidTestModel;
        GridBagConstraints constraints = setBasicStyle(panel);
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setSize(800, 600);

        constraints.gridx = 0;
        constraints.gridy = 1;
        panel.add(symptomLabel, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        panel.add(fluCheckBox, constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;
        panel.add(coughCheckBox, constraints);

        constraints.gridx = 0;
        constraints.gridy = 4;
        panel.add(feverCheckBox, constraints);

        constraints.gridx = 0;
        constraints.gridy = 5;
        panel.add(headacheCheckBox, constraints);

        constraints.gridx = 0;
        constraints.gridy = 6;
        panel.add(chestPainCheckBox, constraints);

        constraints.gridx = 0;
        constraints.gridy = 7;
        panel.add(diarrhoeaCheckBox, constraints);

        constraints.gridx = 0;
        constraints.gridy = 8;
        panel.add(button, constraints);


        cmb.addItem(EMPTY_OPTION);
        cmb.addItem("PCR");
        cmb.addItem("RAT");



        GridBagConstraints constraints2 = setBasicStyle(panel1);
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setSize(800, 600);

        constraints.gridx = 0;
        constraints.gridy = 0;
        panel1.add(userNameLabel, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        panel1.add(userNameTextField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        panel1.add(state, constraints);



        constraints2.gridx = 0;
        constraints2.gridy = 5;
        panel1.add(decisionLabel, constraints2);

        constraints2.gridx = 0;
        constraints2.gridy = 6;
        panel1.add(cmb, constraints2);

        constraints2.gridx = 0;
        constraints2.gridy = 7;
        panel1.add(new JLabel(), constraints2);

        constraints2.gridx = 0;
        constraints2.gridy = 8;
        panel1.add(bookingIdLabel, constraints2);

        constraints2.gridx = 0;
        constraints2.gridy = 9;
        panel1.add(button2, constraints2);

        constraints2.gridx = 0;
        constraints2.gridy = 10;
        panel1.add(bookingIdTextField, constraints2);

        constraints2.gridx = 0;
        constraints2.gridy = 11;
        panel1.add(button3, constraints2);



        jp.setLayout(new ScrollPaneLayout());
        bookings.setEditable(false);
        jp.setViewportView(bookings);




        tabbedPanel.addTab(TAG1,panel);
        tabbedPanel.addTab(TAG3,jp);
        tabbedPanel.addTab(TAG2,panel1);




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
            state.setText("Bookings information are available now in the "+TAG3+" tab");
        }else{
            bookings.setForeground(new Color(100, 2, 2));
            bookings.setText("No bookings found for this customer");
            state.setForeground(new Color(100, 2, 2));
            state.setText("No bookings found for this customer");
        }

        // if creating covid-test is done
        if (covidTestModel.isCreated){

            panel.removeAll();
            state.setText("Have create a Covid-test! (you could close the window now)");


            panel1.removeAll();
            panel1.add(state);

            bookings.setText(state.getText());
            panel.update(panel.getGraphics());
            panel1.update(panel1.getGraphics());
        }else{
            if (!covidTestModel.getResponseMessage().equals("")){
                JOptionPane.showMessageDialog(this, covidTestModel.getResponseMessage());
            }
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

    public int countCheckBox(){

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

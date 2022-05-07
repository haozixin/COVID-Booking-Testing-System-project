package views;

import models.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Objects;

public class InterviewView extends View {
    private User theModel;

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
    private JTextField userNameTextField = new JTextField(20);
    private JLabel bookingsLabel = new JLabel("");//The bookings for the user are here:
    private JTextArea bookings = new JTextArea("Nothing now, please go to \"Create COVID-test window\" and click the \"Get existing bookings information\" button first");
    private JLabel bookingIdLabel = new JLabel("Enter bookingId:");
    private JTextField bookingIdTextField = new JTextField(20);
    private JButton button2 = new JButton("Get existing bookings information");


    public InterviewView(User theModel) {
        super("On-site Testing Subsystem - Interview operation");

        this.theModel = theModel;
        GridBagConstraints constraints = setBasicStyle(panel,800, 600);

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

        cmb.addItem("--select one---");
        cmb.addItem("PCR test");
        cmb.addItem("RAT test");



        GridBagConstraints constraints2 = setBasicStyle(panel1,800, 600);

        constraints.gridx = 0;
        constraints.gridy = 0;
        panel1.add(userNameLabel, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        panel1.add(userNameTextField, constraints);

        constraints2.gridx = 0;
        constraints2.gridy = 2;
        panel1.add(button2, constraints2);


        constraints2.gridx = 0;
        constraints2.gridy = 5;
        panel1.add(decisionLabel, constraints2);

        constraints2.gridx = 0;
        constraints2.gridy = 6;
        panel1.add(cmb, constraints2);

        constraints2.gridx = 0;
        constraints2.gridy = 7;
        panel1.add(bookingIdLabel, constraints2);

        constraints2.gridx = 0;
        constraints2.gridy = 8;
        panel1.add(bookingIdTextField, constraints2);



        jp.setLayout(new ScrollPaneLayout());
        bookings.setEditable(false);
        jp.setViewportView(bookings);




        tabbedPanel.addTab("Interview",panel);
        tabbedPanel.addTab("Create COVID-Test",panel1);
        tabbedPanel.addTab("View bookings of the user",jp);





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

        }else{
            bookings.setForeground(new Color(100, 2, 2));
            bookings.setText("No bookings found for this customer");
        }


////        panel.removeAll();
//        panel.add(state);
//        state.setText("you have signed up successfully! (you can close the window now)");
//        // remove button from panel
//        panel.update(panel.getGraphics());
    }

    @Override
    public void addButtonListener(ActionListener listener) {
        button.addActionListener(listener);
    }

    public void addButton2Listener(ActionListener listener) {
        button2.addActionListener(listener);
    }


}

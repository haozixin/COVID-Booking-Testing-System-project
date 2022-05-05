package views;

import models.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.lang.reflect.Method;

public class SignUpView extends JFrame {
    private User theModel;

    private JPanel panel = new JPanel();
    private JLabel state = new JLabel("");
    private JLabel userNameLabel = new JLabel("Username:");
    private JTextField userNameField = new JTextField(15);
    private JLabel passwordLabel = new JLabel("Password:");
    private JPasswordField passwordField = new JPasswordField(15);
    private JLabel givenNameLabel = new JLabel("Given Name");
    private JTextField givenNameField = new JTextField(10);
    private JLabel familyNameLabel = new JLabel("Family Name");
    private JTextField familyNameField = new JTextField(10);
    private JLabel phoneNumberLabel = new JLabel("Phone Number");
    private JTextField phoneNumberField = new JTextField(10);
    private JButton button = new JButton("Sign Up");

    public SignUpView(User model) throws HeadlessException {
        super("Login Subsystem - Sign Up operation");

        this.theModel = model;

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500, 450);
        panel.setLayout(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(10, 10, 10, 10);

        setGridConstraint(constraints, 0, userNameLabel, userNameField);

        setGridConstraint(constraints, 1, passwordLabel, passwordField);

        setGridConstraint(constraints, 2, givenNameLabel, givenNameField);

        setGridConstraint(constraints, 3, familyNameLabel, familyNameField);

        setGridConstraint(constraints, 4, phoneNumberLabel, phoneNumberField);

        constraints.gridx = 0;
        constraints.gridy = 5;
        panel.add(state, constraints);

        constraints.gridx = 1;
        constraints.gridy = 6;
        panel.add(button, constraints);


        this.add(panel);
    }

    private void setGridConstraint(GridBagConstraints constraints, int i, JLabel phoneNumberLabel, JTextField phoneNumberField) {
        constraints.gridx = 0;
        constraints.gridy = i;
        panel.add(phoneNumberLabel, constraints);
        constraints.gridx = 1;
        constraints.gridy = i;
        panel.add(phoneNumberField, constraints);
    }

    public String getUserName() {
        return userNameField.getText();
    }
    public String getPassword() {
        String password = "";
        for (char c : passwordField.getPassword()) {
            password += c;
        }
        return password;
    }
    public String getGivenName() {
        return givenNameField.getText();
    }
    public String getFamilyName() {
        return familyNameField.getText();
    }
    public String getPhoneNumber() {
        return phoneNumberField.getText();
    }

    public void update(boolean result) {
        state.setFont(new Font("Arial", Font.BOLD, 14));
        state.setForeground(new Color(	0, 139, 69));
        if(result) {
            panel.removeAll();
            panel.add(state);
            state.setText("you have signed up successfully! (you can close the window now)");
            // remove button from panel
            panel.update(panel.getGraphics());
        }
    }

    public void update(){}


    public void addButtonListener(ActionListener listener) {
        button.addActionListener(listener);
    }


}

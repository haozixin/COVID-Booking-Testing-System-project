package views;

import models.users.UserModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * The view about the SignUpService
 */
public class SignUpView extends View {
    private final UserModel theModel;


    JLabel userNameLabel = new JLabel("Username");
    private final JTextField userNameField = new JTextField(15);
    private final JLabel passwordLabel = new JLabel("Password");
    private final JPasswordField passwordField = new JPasswordField(15);
    private final JLabel givenNameLabel = new JLabel("Given Name");
    private final JTextField givenNameField = new JTextField(10);
    private final JLabel familyNameLabel = new JLabel("Family Name");
    private final JTextField familyNameField = new JTextField(10);
    private final JLabel phoneNumberLabel = new JLabel("Phone Number");
    private final JTextField phoneNumberField = new JTextField(10);
    private final JButton button = new JButton("Sign Up");

    public SignUpView(UserModel model) throws HeadlessException {
        super("Login Subsystem - Sign Up operation");

        this.theModel = model;
        GridBagConstraints constraints = setBasicStyle(panel);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(600,400);

        addOneComponent(constraints, 0, userNameLabel, userNameField);

        addOneComponent(constraints, 1, passwordLabel, passwordField);

        addOneComponent(constraints, 2, givenNameLabel, givenNameField);

        addOneComponent(constraints, 3, familyNameLabel, familyNameField);

        addOneComponent(constraints, 4, phoneNumberLabel, phoneNumberField);

        constraints.gridx = 0;
        constraints.gridy = 5;
        panel.add(state, constraints);

        constraints.gridx = 1;
        constraints.gridy = 6;
        panel.add(button, constraints);


        this.add(panel);
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

    @Override
    public void update(boolean result) {
        if(result) {
            panel.removeAll();
            panel.add(state);
            state.setText("you have signed up successfully! (you can close the window now)");
            // remove button from panel
            panel.update(panel.getGraphics());
        }
    }

    @Override
    public void update(){}

    @Override
    public void addButtonListener(ActionListener listener) {
        button.addActionListener(listener);
    }


}

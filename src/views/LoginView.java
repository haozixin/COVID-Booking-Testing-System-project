package views;

import models.Actor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class LoginView extends View {
    private Actor theModel;


    private JLabel UserNameLabel = new JLabel("Username:");
    private JLabel PasswordLabel = new JLabel("Password:");
    private JLabel state = new JLabel("You are not logged in");
    private JTextField userNameField = new JTextField(15);
    private JPasswordField passwordField = new JPasswordField(15);
    private JButton button = new JButton("Login");

    public LoginView(Actor model) throws HeadlessException {
        super("Login Subsystem - Login operation");


        this.theModel = model;
        GridBagConstraints constraints = setBasicStyle(600,250);

        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(UserNameLabel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 0;
        panel.add(userNameField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        panel.add(PasswordLabel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 1;
        panel.add(passwordField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        panel.add(state, constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;
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

    @Override
    public void update(){
        if (theModel.isLoggedIn()) {

            panel.removeAll();
            panel.add(state);
            state.setText("Hello "+theModel.getName()+", you are logged in! (you can close the window now)");
            panel.update(panel.getGraphics());
        }
    }

    @Override
    public void addButtonListener(ActionListener listener) {
        button.addActionListener(listener);
    }
}

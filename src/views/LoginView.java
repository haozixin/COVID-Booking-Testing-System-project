package views;

import models.Actor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class LoginView extends JFrame {
    private Actor theModel;

    private JPanel panel;
    private JLabel UserNameLabel;
    private JLabel PasswordLabel;
    private JLabel state;
    private JTextField userNameField;
    private JPasswordField passwordField;
    private JButton button;

    public LoginView(Actor model) throws HeadlessException {
        super("Login Subsystem - Login operation");

        UserNameLabel = new JLabel("Username:");
        PasswordLabel = new JLabel("Password:");
        state = new JLabel("You are not logged in");
        userNameField = new JTextField(15);
        passwordField = new JPasswordField(15);
        button = new JButton("Login");

        this.theModel = model;
        panel = new JPanel();

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 250);
        panel.setLayout(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(10, 10, 10, 10);

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

    public void update(){
        if (theModel.isLoggedIn()) {

            panel.removeAll();
            panel.add(state);
            state.setText("Hello "+theModel.getName()+", you are logged in! (you can close the window now)");
            panel.update(panel.getGraphics());
        }
    }

    public void addButtonListener(ActionListener listener) {
        button.addActionListener(listener);
    }
}

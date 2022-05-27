package views;

import engine.CurrentOperator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * The view about the LoginService
 */
public class LoginView extends View {
    private CurrentOperator theModel;


    private JLabel userNameLabel = new JLabel("Username:");
    private JLabel passwordLabel = new JLabel("Password:");
    private JTextField userNameField = new JTextField(15);
    private JPasswordField passwordField = new JPasswordField(15);
    private JButton button = new JButton("Login");

    public LoginView(CurrentOperator model) throws HeadlessException {
        super("Login Subsystem - Login operation");

        state = new JLabel("You are not logged in");
        this.theModel = model;
        GridBagConstraints constraints = setBasicStyle(panel);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(600,250);

        addOneComponent(constraints, 0, userNameLabel, userNameField);

        addOneComponent(constraints, 1, passwordLabel, passwordField);

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
        }else{
            state.setText("Wrong username or password");
            panel.update(panel.getGraphics());
        }

    }

    @Override
    public void addButtonListener(ActionListener listener) {
        button.addActionListener(listener);
    }
}

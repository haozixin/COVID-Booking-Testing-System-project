package views;

import models.Actor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ProfileView extends View {
//    private Actor theModel;

//    private JLabel userNameLabel = new JLabel("Username:");
//    private JLabel passwordLabel = new JLabel("Password:");
//    private JTextField userNameField = new JTextField(15);
//    private JPasswordField passwordField = new JPasswordField(15);
//    private JButton button = new JButton("Login");

    private JLabel idLabel = new JLabel("ID:");
    private JLabel givenNameLabel = new JLabel("Given Name:");
//    private JLabel familyNameLabel = new JLabel("Family Name:");
//    private JLabel userNameLabel = new JLabel("Username:");
//    private JLabel phoneNumberLabel = new JLabel("Phone Number:");
//    private JLabel ActiveBookingLabel = new JLabel("Active Booking:");

    private JPasswordField idField = new JPasswordField(15);
    private JPasswordField givenNameField = new JPasswordField(15);

    public ProfileView() throws HeadlessException {
        super("Profile");

        state = new JLabel("You are logged in");
//        this.theModel = model;
        GridBagConstraints constraints = setBasicStyle(panel);
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setSize(600,250);

        addOneComponent(constraints, 0, idLabel, idField);

        addOneComponent(constraints, 1, givenNameLabel, givenNameField);

        constraints.gridx = 0;
        constraints.gridy = 2;
        panel.add(state, constraints);


        this.add(panel);
    }

    @Override
    public void update() {

    }

    @Override
    public void addButtonListener(ActionListener listener) {

    }

//    public String getUserName() {
//        return userNameField.getText();
//    }
//    public String getPassword() {
//        String password = "";
//        for (char c : passwordField.getPassword()) {
//            password += c;
//        }
//        return password;
//    }
//
//    @Override
//    public void update(){
//        if (theModel.isLoggedIn()) {
//
//            panel.removeAll();
//            panel.add(state);
//            state.setText("Hello "+theModel.getName()+", you are logged in! (you can close the window now)");
//            panel.update(panel.getGraphics());
//        }else{
//            state.setText("Wrong username or password");
//            panel.update(panel.getGraphics());
//        }
//
//    }
//
//    @Override
//    public void addButtonListener(ActionListener listener) {
//        button.addActionListener(listener);
//    }
}

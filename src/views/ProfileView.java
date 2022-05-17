package views;


import models.CovidTestingSiteModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

public class ProfileView extends View {
//    private Actor theModel;

//    private JLabel userNameLabel = new JLabel("Username:");
//    private JLabel passwordLabel = new JLabel("Password:");
//    private JTextField userNameField = new JTextField(15);
//    private JPasswordField passwordField = new JPasswordField(15);
//    private JButton button = new JButton("Login");

//    private JLabel idLabel = new JLabel("ID:");
//    private JLabel givenNameLabel = new JLabel("Given Name:");
//    private JLabel familyNameLabel = new JLabel("Family Name:");
//    private JLabel userNameLabel = new JLabel("Username:");
//    private JLabel phoneNumberLabel = new JLabel("Phone Number:");
//    private JLabel activeBookingLabel = new JLabel("Active Booking:");
//
//    private JPasswordField idField = new JPasswordField(15);
//    private JPasswordField givenNameField = new JPasswordField(15);
//    private JPasswordField familyNameField = new JPasswordField(15);
//    private JPasswordField userNameField = new JPasswordField(15);
//    private JPasswordField phoneNumberField = new JPasswordField(15);
//    private JPasswordField activeBookingField = new JPas5swordField(15);
    private JPanel p1 = new JPanel();
    private JPanel p2 = new JPanel();

    private JTextArea bookings = new JTextArea("Here is the place to list active bookings");
    private JScrollPane jp = new JScrollPane(bookings);

    private JLabel idLabel = new JLabel("ID:");
    private JLabel givenNameLabel = new JLabel("Given Name:");
    private JLabel familyNameLabel = new JLabel("Family Name:");
    private JLabel userNameLabel = new JLabel("Username:");
    private JLabel phoneNumberLabel = new JLabel("Phone Number:");
    private JLabel activeBookingLabel = new JLabel("Active Booking:");



    public ProfileView() throws HeadlessException {
        super("Profile");

        GridBagConstraints c = setBasicStyle(panel);
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setSize(1000,800);
        bookings.setEditable(false);
        jp.setLayout(new ScrollPaneLayout());
        jp.setPreferredSize(new Dimension(800,300));
        bookings.setSize(800,300);


        GridBagConstraints c1 = setBasicStyle(p1);
        GridBagConstraints c2 = setBasicStyle(p2);


        c1.gridx = 0;
        c1.gridy = 0;
        p1.add(idLabel, c1);

        c1.gridx = 0;
        c1.gridy = 1;
        p1.add(givenNameLabel, c1);

        c1.gridx = 0;
        c1.gridy = 2;
        p1.add(familyNameLabel, c1);

        c1.gridx = 0;
        c1.gridy = 3;
        p1.add(userNameLabel, c1);

        c1.gridx = 0;
        c1.gridy = 4;
        p1.add(phoneNumberLabel, c1);

        c1.gridx = 0;
        c1.gridy = 5;
        p1.add(activeBookingLabel, c1);

        c2.gridx = 0;
        c2.gridy = 0;
        p2.add(jp, c);

        c.gridx = 0;
        c.gridy = 0;
        panel.add(p1, c);

        c.gridx = 0;
        c.gridy = 1;
        panel.add(p2, c);

        add(panel);
//        super("Profile");
////        this.theModel = model;
//        GridBagConstraints constraints = setBasicStyle(panel);
//        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
//        this.setSize(600,250);
//
//        addOneComponent(constraints, 0, idLabel, idField);
//
//        addOneComponent(constraints, 1, givenNameLabel, givenNameField);
//
//        addOneComponent(constraints, 2, familyNameLabel, familyNameField);
//
//        addOneComponent(constraints, 3, userNameLabel, userNameField);
//
//        addOneComponent(constraints, 4, phoneNumberLabel, phoneNumberField);
//
//        addOneComponent(constraints, 5, activeBookingLabel, activeBookingField);
//
//        constraints.gridx = 0;
//        constraints.gridy =5;
//        panel.add(state, constraints);
//
//
//        this.add(panel);
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

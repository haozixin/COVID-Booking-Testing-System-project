package controllers;

import models.Model;
import models.User;
import views.SignUpView;
import views.View;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignUpController extends Controller {
    private SignUpView signUpView;
    private User user;

    public SignUpController(SignUpView signUpView, User user) {
        if (signUpView != null && user != null) {
            this.signUpView = signUpView;
            this.user = user;
            this.signUpView.addButtonListener(new SignUpListener());
        }else{
            throw new IllegalArgumentException("Arguments must be of type SignUpView and User");
        }

    }

    class SignUpListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (!signUpView.getUserName().equals("")
                    && !signUpView.getPassword().equals("")
                    && !signUpView.getGivenName().equals("")
                    && !signUpView.getFamilyName().equals("")
                    && !signUpView.getPhoneNumber().equals("")) {
                String username = signUpView.getUserName();
                String password = signUpView.getPassword();
                String givenName = signUpView.getGivenName();
                String familyName = signUpView.getFamilyName();
                String phoneNumber = signUpView.getPhoneNumber();
                user.setSchema(phoneNumber, password, username, familyName, givenName);
                boolean result = user.updateToServer();
                signUpView.update(result);
            }
            else{
                JOptionPane.showMessageDialog(null, "All fields must be filled");
            }


        }
    }
}

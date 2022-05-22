package controllers;

import models.users.UserModel;
import views.SignUpView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignUpController extends Controller {
    private SignUpView signUpView;
    private UserModel userModel;

    public SignUpController(SignUpView signUpView, UserModel userModel) {
        if (signUpView != null && userModel != null) {
            this.signUpView = signUpView;
            this.userModel = userModel;
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
                userModel.setSchema(phoneNumber, password, username, familyName, givenName);
                boolean result = userModel.updateToServer();
                signUpView.update(result);
            }
            else{
                JOptionPane.showMessageDialog(null, "All fields must be filled");
            }


        }
    }
}

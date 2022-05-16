package controllers;


import engine.CurrentOperator;
import views.LoginView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class LoginController extends Controller{
    private LoginView loginView;
    private CurrentOperator loginModel;

    public LoginController(LoginView loginView, CurrentOperator loginModel) {
        if (loginView != null && loginModel != null) {
            this.loginView = loginView;
            this.loginModel = loginModel;
            this.loginView.addButtonListener(new LoginListener());
        }
        else{
            throw new IllegalArgumentException("Arguments must be of type LoginView and Actor");
        }

    }

    class LoginListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (loginView.getUserName().equals("") || loginView.getPassword().equals("")) {
                JOptionPane.showMessageDialog(null, "Please enter your username and password");
            }else{
                String username = loginView.getUserName();
                String password = loginView.getPassword();

                // deal with the model
                try {
                    loginModel.login(username, password);
                } catch (IOException | InterruptedException ex) {
                    ex.printStackTrace();
                }
                loginView.update();
            }
        }
    }


}
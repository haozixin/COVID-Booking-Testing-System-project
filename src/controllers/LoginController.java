package controllers;


import models.Actor;
import views.LoginView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class LoginController {
    private LoginView loginView;
    private Actor loginModel;

    public LoginController(LoginView loginView, Actor loginModel) {
        this.loginView = loginView;
        this.loginModel = loginModel;
        this.loginView.addLoginButtonListener(new LoginListener());
    }

    class LoginListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
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
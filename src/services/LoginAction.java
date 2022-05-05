package services;

import controllers.LoginController;
import engine.Action;
import models.Actor;
import views.LoginView;

import java.io.IOException;

public class LoginAction extends Action {

    @Override
    public String execute(Actor actor) throws IOException, InterruptedException {
        LoginView loginView = new LoginView(actor);
        LoginController controller = new LoginController(loginView, actor);
        loginView.update();
        loginView.setVisible(true);
        return "";
    }

    @Override
    public String menuDescription(Actor actor) {
        return "Go to Login";
    }
}

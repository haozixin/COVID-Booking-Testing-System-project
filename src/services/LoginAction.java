package services;

import controllers.LoginController;
import engine.Action;
import models.Actor;
import views.LoginView;

import java.io.IOException;

public class LoginAction extends Action {
    @Override
    public String execute(Actor actor){
        view = new LoginView(actor);
        controller = new LoginController(view, actor);
        view.update();
        view.setVisible(true);
        return "";
    }

    @Override
    public String menuDescription(Actor actor) {
        return "Go to Login";
    }
}

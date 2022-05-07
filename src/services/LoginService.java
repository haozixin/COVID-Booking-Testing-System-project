package services;

import controllers.LoginController;
import engine.Service;
import models.Actor;
import views.LoginView;

public class LoginService extends Service {
    @Override
    public String execute(Actor actor){
        LoginView view = new LoginView(actor);
        controller = new LoginController(view, actor);
        view.setVisible(true);
        return "";
    }

    @Override
    public String menuDescription(Actor actor) {
        return "Go to Login";
    }
}

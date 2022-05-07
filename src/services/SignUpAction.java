package services;

import controllers.Controller;
import controllers.SignUpController;
import engine.Action;
import models.Actor;
import models.User;
import views.SignUpView;
import views.View;


import java.io.IOException;

public class SignUpAction extends Action {


    @Override
    public String execute(Actor actor){
        User userModel = new User();
        view = new SignUpView(userModel);
        controller = new SignUpController(view, userModel);
        view.update();
        view.setVisible(true);
        return "";
    }

    @Override
    public String menuDescription(Actor actor) {
        return "Go to Sign Up";
    }
}

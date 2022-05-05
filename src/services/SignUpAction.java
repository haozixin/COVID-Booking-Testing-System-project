package services;

import controllers.SignUpController;
import engine.Action;
import models.Actor;
import models.User;
import views.SignUpView;


import java.io.IOException;

public class SignUpAction extends Action {

    @Override
    public String execute(Actor actor) throws IOException, InterruptedException {
        User userModel = new User();
        SignUpView signUpView = new SignUpView(userModel);
        SignUpController controller = new SignUpController(signUpView, userModel);
        signUpView.update();
        signUpView.setVisible(true);
        return "";
    }

    @Override
    public String menuDescription(Actor actor) {
        return "Go to Sign Up";
    }
}

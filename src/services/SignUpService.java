package services;

import controllers.SignUpController;
import engine.Service;
import models.Actor;
import models.User;
import views.SignUpView;

public class SignUpService extends Service {


    @Override
    public String execute(Actor actor){
        User userModel = new User();
        SignUpView view = new SignUpView(userModel);
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

package services;

import controllers.SignUpController;
import engine.Service;
import engine.CurrentOperator;
import models.User;
import views.SignUpView;

public class SignUpService extends Service {


    @Override
    public String execute(CurrentOperator currentOperator){
        User userModel = new User();
        SignUpView view = new SignUpView(userModel);
        controller = new SignUpController(view, userModel);
        view.setVisible(true);
        return "";
    }

    @Override
    public String menuDescription(CurrentOperator currentOperator) {
        return "Go to Sign Up";
    }
}

package services;

import controllers.SignUpController;
import engine.Service;
import engine.CurrentOperator;
import models.UserModel;
import views.SignUpView;

/**
 * Action to sign up the new user
 */
public class SignUpService extends Service {

    @Override
    public String execute(CurrentOperator currentOperator){
        UserModel userModel = new UserModel();
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

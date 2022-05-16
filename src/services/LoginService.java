package services;

import controllers.LoginController;
import engine.Service;
import engine.CurrentOperator;
import views.LoginView;

public class LoginService extends Service {
    @Override
    public String execute(CurrentOperator currentOperator){
        LoginView view = new LoginView(currentOperator);
        controller = new LoginController(view, currentOperator);
        view.setVisible(true);
        return "";
    }

    @Override
    public String menuDescription(CurrentOperator currentOperator) {
        return "Go to Login";
    }
}

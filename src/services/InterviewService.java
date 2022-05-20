package services;

import controllers.InterviewController;
import engine.CurrentOperator;
import engine.Service;
import models.*;
import views.InterviewView;

/**
 * Action to interview with the resident
 */
public class InterviewService extends Service {
    @Override
    public String execute(CurrentOperator currentOperator){
        UserModel userModel = new UserModel();
        CovidTestModel covidTestModel = new CovidTestModel();
        InterviewView view = new InterviewView(userModel, covidTestModel);

        controller = new InterviewController(view, userModel, covidTestModel);
        view.setVisible(true);
        return "";
    }

    @Override
    public String menuDescription(CurrentOperator currentOperator) {
        return "Go to conduct an interview";
    }
}

package services;

import controllers.InterviewController;
import engine.CurrentOperator;
import engine.Service;
import models.*;
import views.InterviewView;

public class InterviewService extends Service {
    @Override
    public String execute(CurrentOperator currentOperator){
        User userModel = new User();
        CovidTestModel covidTestModel = new CovidTestModel();
        InterviewView view = new InterviewView(userModel, covidTestModel);

        controller = new InterviewController(view, userModel, covidTestModel);
        view.setVisible(true);
        return "";
    }

    @Override
    public String menuDescription(CurrentOperator currentOperator) {
        return "Go to conduct a interview";
    }
}

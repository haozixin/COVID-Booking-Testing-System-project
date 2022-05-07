package services;

import controllers.InterviewController;
import engine.Service;
import models.*;
import views.InterviewView;

public class InterviewService extends Service {
    @Override
    public String execute(Actor actor){
        User userModel = new User();
        CovidTest covidTestModel = new CovidTest();
        InterviewView view = new InterviewView(userModel, covidTestModel);

        controller = new InterviewController(view, userModel, covidTestModel);
        view.setVisible(true);
        return "";
    }

    @Override
    public String menuDescription(Actor actor) {
        return "Go to conduct a interview";
    }
}

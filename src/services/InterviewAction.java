package services;

import controllers.InterviewController;
import engine.Action;
import models.Actor;
import models.Collection;
import models.Model;
import models.User;
import views.InterviewView;

public class InterviewAction extends Action{
    @Override
    public String execute(Actor actor){
        User dataModel = new User();
        view = new InterviewView(dataModel);

        controller = new InterviewController(view, dataModel);
        view.setVisible(true);
        return "";
    }

    @Override
    public String menuDescription(Actor actor) {
        return "Go to conduct a interview";
    }
}

package services;

import controllers.InterviewController;
import controllers.LoginController;
import engine.Action;
import engine.Actions;
import models.Actor;
import views.InterviewView;
import views.LoginView;

import java.io.IOException;
import java.util.List;

public class InterviewAction extends Action{
    @Override
    public String execute(Actor actor){
        view = new InterviewView(actor);
        controller = new InterviewController(view, actor);
        view.update();
        view.setVisible(true);
        return "";
    }

    @Override
    public String menuDescription(Actor actor) {
        return "Go to conduct a interview";
    }
}

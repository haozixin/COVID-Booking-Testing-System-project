package actions;

import actors.Actor;
import engine.actions.Action;
import user.User;

import java.io.IOException;

public class GoBackAction extends Action {
    private Actor actor;

    public GoBackAction() {
        actor = Actor.getInstance();

    }

    @Override
    public String execute(Actor actor) {
        actor.setGoBack(true);
        return menuDescription(actor);
    }

    @Override
    public String menuDescription(Actor actor) {
        return "Go back to previous menu";
    }


}

package services;


import engine.Action;
import models.Actor;

/**
 * Action to go back to the previous room.
 */
public class GoBackAction extends Action {
    private Actor actor;

    /**
     * Constructor.
     */
    public GoBackAction() {
        actor = Actor.getInstance();

    }

    @Override
    public String execute(Actor actor) {
        actor.setWantsGoBack(true);
        return menuDescription(actor);
    }

    @Override
    public String menuDescription(Actor actor) {
        return "Go back to previous menu";
    }


}

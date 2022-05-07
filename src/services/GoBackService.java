package services;


import engine.Service;
import models.Actor;

/**
 * Action to go back to the previous room.
 */
public class GoBackService extends Service {
    private Actor actor;

    /**
     * Constructor.
     */
    public GoBackService() {
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

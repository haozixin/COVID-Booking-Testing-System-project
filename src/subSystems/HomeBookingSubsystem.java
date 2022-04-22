package subSystems;

import actors.Actor;
import engine.Display;
import engine.actions.Action;
import engine.actions.Actions;

import java.io.IOException;

public class HomeBookingSubsystem extends CovidBAndTSystem{

    public HomeBookingSubsystem() {
        super();
    }

    @Override
    public void run()
        {
        if (actor == null)
            throw new IllegalStateException();

        // This loop is basically the whole system
        while (actor.getLogged()) {
            processActorTurn(actor);
        }

    }

    @Override
    protected void processActorTurn(Actor actor)
        {
            actions.clear();
        // TODO: add actions
        //

        super.processActorTurn(actor);
    }
}

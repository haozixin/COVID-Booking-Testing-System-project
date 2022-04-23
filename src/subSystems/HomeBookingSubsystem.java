package subSystems;

import actors.Actor;

public class HomeBookingSubsystem extends CovidBAndTSystem{

    public HomeBookingSubsystem() {
        super();
        systemName = "Home-Booking Subsystem";
    }

    @Override
    public void run()
        {
        if (actor == null)
            throw new IllegalStateException();

        // This loop is basically the whole system
        while (!actor.getIsGoBack()) {
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

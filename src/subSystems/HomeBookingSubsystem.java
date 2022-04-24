package subSystems;

import actors.Actor;

public class HomeBookingSubsystem extends CovidBAndTSystem{

    public HomeBookingSubsystem() {
        super();
        systemName = "Home-Booking Subsystem";
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

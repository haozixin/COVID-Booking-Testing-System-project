package subSystems;

import actions.GoBackAction;
import actions.HomeBooking;
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
        actions.add(new GoBackAction());
        actions.add(new HomeBooking());


        super.processActorTurn(actor);
    }
}

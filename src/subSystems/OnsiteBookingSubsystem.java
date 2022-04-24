package subSystems;

import actions.BookingTestAction;
import actions.CheckBooking;
import actions.GoBackAction;
import actors.Actor;

public class OnsiteBookingSubsystem extends CovidBAndTSystem{

    public OnsiteBookingSubsystem() {
        super();
        systemName = "Onsite Booking Subsystem";
        setDashBoard();
    }

    @Override
    protected void processActorTurn(Actor actor)
        {
            actions.clear();
            actions.add(new GoBackAction());
            actions.add(new CheckBooking());
            actions.add(new BookingTestAction());

        super.processActorTurn(actor);
    }
}

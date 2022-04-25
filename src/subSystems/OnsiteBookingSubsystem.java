package subSystems;

import actions.BookingTestAction;
import actions.CheckBookingAction;
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

            if (actor.isAdministrator()){
                actions.add(new CheckBookingAction());
                actions.add(new BookingTestAction());
            }
            actions.add(new GoBackAction());


        super.processActorTurn(actor);
    }
}

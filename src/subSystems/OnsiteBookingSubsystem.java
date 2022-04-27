package subSystems;

import actions.OnSiteBookingAction;
import actions.CheckBookingAction;
import actions.GoBackAction;
import actors.Actor;

/**
 * This class is the OnsiteBooking Subsystem.
 */
public class OnsiteBookingSubsystem extends CovidBAndTSystem{

    /**
     * Constructor
     */
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
                actions.add(new OnSiteBookingAction());
            }
            actions.add(new GoBackAction());


        super.processActorTurn(actor);
    }
}

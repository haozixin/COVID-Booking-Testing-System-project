package subSystems;


import models.Actor;
import services.CheckBookingService;
import services.GoBackService;
import services.OnSiteBookingService;

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
            services.clear();

            if (actor.isAdministrator()){
                services.add(new CheckBookingService());
                services.add(new OnSiteBookingService());
            }

            services.add(new GoBackService());


        super.processActorTurn(actor);
    }
}

package subSystems;


import engine.CurrentOperator;
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
    protected void processActorTurn(CurrentOperator currentOperator)
        {
            services.clear();

            if (currentOperator.isAdministrator()){
                services.add(new CheckBookingService());
                services.add(new OnSiteBookingService());
            }

            services.add(new GoBackService());


        super.processActorTurn(currentOperator);
    }
}

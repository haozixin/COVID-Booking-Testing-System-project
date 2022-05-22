package subSystems;


import engine.CurrentOperator;
import services.*;

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
            if (currentOperator.isLoggedIn()){

                services.add(new ChangeBookingService());
            }


            services.add(new GoBackService());


        super.processActorTurn(currentOperator);
    }
}

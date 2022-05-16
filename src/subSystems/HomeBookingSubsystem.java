package subSystems;


import engine.CurrentOperator;
import services.GoBackService;
import services.HomeBookingService;
import services.ScanQRCodeService;

/**
 * HomeBookingSubsystem is a subsystem
 */
public class HomeBookingSubsystem extends CovidBAndTSystem {

    public HomeBookingSubsystem() {
        super();
        systemName = "Home-Booking Subsystem";
        setDashBoard();
    }

    @Override
    protected void processActorTurn(CurrentOperator currentOperator) {
        services.clear();

//        if (actor.isAdministrator()) {
//            services.add(new ScanQRcodeAction());
//        }
//
//        if (actor.isLoggedIn()){
//            services.add(new HomeBookingAction());
//        }
        services.add(new HomeBookingService());
        services.add(new ScanQRCodeService());
        services.add(new GoBackService());

        super.processActorTurn(currentOperator);
    }
}

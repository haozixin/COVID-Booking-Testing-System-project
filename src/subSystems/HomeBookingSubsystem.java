package subSystems;


import models.Actor;
import services.GoBackService;
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
    protected void processActorTurn(Actor actor) {
        services.clear();

//        if (actor.isAdministrator()) {
//            services.add(new ScanQRcodeAction());
//        }
//
//        if (actor.isLoggedIn()){
//            services.add(new HomeBookingAction());
//        }
        services.add(new ScanQRCodeService());
        services.add(new GoBackService());

        super.processActorTurn(actor);
    }
}

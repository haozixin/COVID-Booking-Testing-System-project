package subSystems;

import actions.GoBackAction;
import actions.HomeBookingAction;
import actions.ScanQRcodeAction;
import actors.Actor;

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
        actions.clear();

        if (actor.isAdministrator()) {
            actions.add(new ScanQRcodeAction());
        }
        actions.add(new GoBackAction());
        if (actor.getLoginState()){
            actions.add(new HomeBookingAction());
        }


        super.processActorTurn(actor);
    }
}

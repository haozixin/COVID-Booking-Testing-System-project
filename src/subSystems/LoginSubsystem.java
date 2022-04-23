package subSystems;


import actions.GoBackAction;
import actions.GoSubsystem;
import actions.LoginAction;
import actions.SignUpAction;
import actors.Actor;
import utility.*;

/**
 * The class is responsible for loginSubsystem
 */
public class LoginSubsystem extends CovidBAndTSystem{

    public LoginSubsystem() {
        super();
        systemName = "Login Subsystem";
        dashBoard = Utility.setDashboard(80,5, systemName);
    }

    @Override
    protected void processActorTurn(Actor actor)
        {
            actions.clear();
        // TODO: add actions
            actions.add(new LoginAction());
            actions.add(new SignUpAction());
            actions.add(new GoBackAction());

        super.processActorTurn(actor);
    }
}

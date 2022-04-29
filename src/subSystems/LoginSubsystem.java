package subSystems;


import actions.GoBackAction;
import actions.LoginAction;
import actions.SignUpAction;
import actors.Actor;

/**
 * The class is responsible for loginSubsystem
 */
public class LoginSubsystem extends CovidBAndTSystem{

    public LoginSubsystem() {
        super();
        systemName = "Login Subsystem";
        setDashBoard();
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

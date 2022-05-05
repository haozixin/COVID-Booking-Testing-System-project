package subSystems;


import services.*;
import models.Actor;

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


        super.processActorTurn(actor);
    }
}

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
    public void run()
        {
        if (actor == null)
            throw new IllegalStateException();

        // if the user logged in or wants to go back, the loop will stop
        while (!actor.getIsGoBack()) {
            display();
            processActorTurn(actor);
            //after each time the actor chose go back action, we need to reset the actor's wantsGoBack attibute back to false

        }

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

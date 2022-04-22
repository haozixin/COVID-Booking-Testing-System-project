package subSystems;


import actions.LoginAction;
import actions.SignUpAction;
import actors.Actor;
import engine.actions.Actions;
import utility.*;
import webServiceAPI.ServicesAdapter;

/**
 * The class is responsible for loginSubsystem
 */
public class LoginSubsystem extends CovidBAndTSystem{


    private static final String systemName = "Login Subsystem";

    public LoginSubsystem() {
        super();
        dashBoard = Utility.setDashboard(64,5, systemName);
    }


    public void display() {
        Utility.printArrayList(dashBoard);
    }


    @Override
    public void run()
        {
        if (actor == null)
            throw new IllegalStateException();

        // This loop is basically the whole system
        while (!actor.getLogged()) {
            display();
            processActorTurn(actor);
        }

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

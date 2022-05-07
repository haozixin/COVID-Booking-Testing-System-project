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
            services.clear();

            services.add(new LoginService());
            services.add(new SignUpService());
            services.add(new GoBackService());


        super.processActorTurn(actor);
    }
}

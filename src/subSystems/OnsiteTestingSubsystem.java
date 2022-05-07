package subSystems;


import models.Actor;
import services.GoBackService;
import services.InterviewService;

/**
 * This class represents the Onsite Testing Subsystem.
 */
public class OnsiteTestingSubsystem extends CovidBAndTSystem {

    public OnsiteTestingSubsystem() {
        super();
        systemName = "Onsite Testing Subsystem";
        setDashBoard();
    }

    @Override
    protected void processActorTurn(Actor actor) {
        services.clear();
        if (actor.isAdministrator() || actor.isHealthcareWorker()){
            services.add(new InterviewService());
        }

        services.add(new GoBackService());


        super.processActorTurn(actor);
    }
}

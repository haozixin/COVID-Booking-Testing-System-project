package subSystems;


import models.Actor;
import services.GoBackAction;
import services.InterviewAction;

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
        actions.clear();
        if (actor.isAdministrator() || actor.isHealthcareWorker()){
            actions.add(new InterviewAction());
        }
        actions.add(new GoBackAction());


        super.processActorTurn(actor);
    }
}

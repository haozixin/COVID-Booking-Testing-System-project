package subSystems;

import actions.GoBackAction;
import actions.InterviewAction;
import actors.Actor;

public class OnsiteTestingSubsystem extends CovidBAndTSystem {

    public OnsiteTestingSubsystem() {
        super();
        systemName = "Onsite Testing Subsystem";
        setDashBoard();
    }

    @Override
    protected void processActorTurn(Actor actor) {
        actions.clear();

        actions.add(new GoBackAction());
        actions.add(new InterviewAction());

        super.processActorTurn(actor);
    }
}

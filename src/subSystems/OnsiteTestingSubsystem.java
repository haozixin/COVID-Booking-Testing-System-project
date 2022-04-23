package subSystems;

import actors.Actor;

public class OnsiteTestingSubsystem extends CovidBAndTSystem {

    public OnsiteTestingSubsystem() {
        super();
    }

    @Override
    protected void processActorTurn(Actor actor) {
        actions.clear();

        // TODO: add actions
        //

        super.processActorTurn(actor);
    }
}

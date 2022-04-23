package subSystems;

import actors.Actor;

public class OnsiteTestingSubsystem extends CovidBAndTSystem {

    public OnsiteTestingSubsystem() {
        super();
    }

    @Override
    public void run() {
        if (actor == null)
            throw new IllegalStateException();

        // This loop is basically the whole system
        while (actor.getLoginState()) {
            processActorTurn(actor);
        }

    }

    @Override
    protected void processActorTurn(Actor actor) {
        actions.clear();

        // TODO: add actions
        //

        super.processActorTurn(actor);
    }
}

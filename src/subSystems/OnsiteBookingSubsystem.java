package subSystems;

import actors.Actor;

public class OnsiteBookingSubsystem extends CovidBAndTSystem{

    public OnsiteBookingSubsystem() {
        super();
    }

    @Override
    protected void processActorTurn(Actor actor)
        {
            actions.clear();

        // TODO: add actions
        //

        super.processActorTurn(actor);
    }
}

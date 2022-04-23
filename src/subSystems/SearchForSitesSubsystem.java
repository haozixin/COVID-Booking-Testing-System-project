package subSystems;

import actions.SearchSiteAction;
import actions.ViewSiteListAction;
import actions.GoBackAction;
import actors.Actor;
import utility.Utility;

public class SearchForSitesSubsystem extends CovidBAndTSystem {

    public SearchForSitesSubsystem() {
        super();
        systemName = "Search for testing sites Subsystem";
        dashBoard = Utility.setDashboard(100,5, systemName);
    }


    @Override
    public void run() {
        if (actor == null)
            throw new IllegalStateException();

        // if the actor wants to go back, then the loop will end
        while (!actor.getIsGoBack()) {
            display();
            processActorTurn(actor);


        }
    }

    @Override
    protected void processActorTurn(Actor actor) {
        actions.clear();
        // TODO: add actions
        actions.add(new ViewSiteListAction());
        actions.add(new SearchSiteAction());
        actions.add(new GoBackAction());
        super.processActorTurn(actor);
    }
}

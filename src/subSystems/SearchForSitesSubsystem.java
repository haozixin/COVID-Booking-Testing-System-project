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
        setDashBoard();
    }

    /**
     * arrange the actions(user could choose from menu) for the actor here
     * @param actor the actor who is going to take the turn
     */
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

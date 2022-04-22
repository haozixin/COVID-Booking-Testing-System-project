package subSystems;

import actions.SearchSiteAction;
import actions.ViewSiteListAction;
import actions.GoBackAction;
import actors.Actor;
import utility.Utility;

public class SearchForSitesSubsystem extends CovidBAndTSystem {
    private static final String systemName = "Search for testing sites Subsystem";

    public SearchForSitesSubsystem() {
        super();
        dashBoard = Utility.setDashboard(100,5, systemName);
    }

    public void display() {
        Utility.printArrayList(dashBoard);
    }

    @Override
    public void run() {
        if (actor == null)
            throw new IllegalStateException();

        // This loop is basically the whole system
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

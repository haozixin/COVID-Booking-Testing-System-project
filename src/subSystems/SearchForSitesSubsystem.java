package subSystems;


import models.Actor;
import services.GoBackService;
import services.SearchSiteService;
import services.ViewSiteListService;

/**
 * This class is the subsystem that handles the search for sites.
 */
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
        services.clear();

//        services.add(搜索);
        services.add(new ViewSiteListService());
        services.add(new SearchSiteService());
        services.add(new GoBackService());
        super.processActorTurn(actor);
    }
}

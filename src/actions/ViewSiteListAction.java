package actions;

import actors.Actor;
import engine.DataCollection;
import engine.actions.Action;
import testingSites.SitesCollection;
import utility.Utility;
import java.io.IOException;

/**
 * Action to view the list of sites.
 */
public class ViewSiteListAction extends Action {
    DataCollection sitesCollection;
    String name;

    /**
     * Constructor for ViewSiteListAction.
     */
    public ViewSiteListAction() {
        this.sitesCollection = SitesCollection.getInstance();
        name = "View Site List operation";
    }

    @Override
    public String execute(Actor actor) throws IOException, InterruptedException {
        Utility.displayAction(name);
        sitesCollection.printWholeList();
        return "Have shown the list of sites";
    }

    @Override
    public String menuDescription(Actor actor) {
        return "View Site List";
    }

}

package actions;

import actors.Actor;
import engine.DataCollection;
import engine.actions.Action;
import testingSites.SitesCollection;
import utility.Utility;

import java.io.IOException;

public class ViewSiteListAction extends Action {
    DataCollection sitesCollection;
    String name;

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

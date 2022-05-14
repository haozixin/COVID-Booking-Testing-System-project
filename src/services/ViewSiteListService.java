package services;


import controllers.ViewSiteListController;
import engine.Service;
import models.Actor;
import models.Collection;
import views.DisplaySiteListView;

/**
 * Action to view the list of sites.
 */
public class ViewSiteListService extends Service {

    @Override
    public String execute(Actor actor) {
        Collection collection = new Collection();
        DisplaySiteListView view = new DisplaySiteListView(collection);
        controller = new ViewSiteListController(view, collection);

        view.setVisible(true);
        return "";
    }

    @Override
    public String menuDescription(Actor actor) {
        return "Go to view testing-sites list";
    }
}

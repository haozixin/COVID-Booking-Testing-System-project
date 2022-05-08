package services;

import controllers.SearchSiteController;
import engine.Service;
import enums.Path;
import models.Actor;
import models.Collection;
import views.SearchSitesView;

public class SearchSiteService extends Service{


    @Override
    public String execute(Actor actor) {
        Collection collection = new Collection();
        collection.getAllEntity(Path.SITE.getPath());
        SearchSitesView view = new SearchSitesView(collection);
        controller = new SearchSiteController(view,collection);
        view.setVisible(true);
        return null;
    }

    @Override
    public String menuDescription(Actor actor) {
        return "Search for testing sites";
    }
}

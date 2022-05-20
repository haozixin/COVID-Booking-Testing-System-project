package services;

import controllers.SearchSiteController;
import engine.Service;
import enums.Path;
import engine.CurrentOperator;
import models.CollectionModel;
import views.SearchSitesView;

/**
 * Action to search testing sites
 */
public class SearchSiteService extends Service{

    @Override
    public String execute(CurrentOperator currentOperator) {
        CollectionModel collectionModel = new CollectionModel();
        collectionModel.updateCollection(Path.SITE.getPath());

        SearchSitesView view = new SearchSitesView(collectionModel);
        controller = new SearchSiteController(view, collectionModel);
        view.setVisible(true);
        return null;
    }

    @Override
    public String menuDescription(CurrentOperator currentOperator) {
        return "Search for testing sites";
    }
}

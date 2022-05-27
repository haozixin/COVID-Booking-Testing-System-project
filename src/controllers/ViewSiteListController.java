package controllers;

import enums.Path;
import models.CollectionModel;
import views.DisplaySiteListView;

/**
 * Controller for the DisplaySitesView
 */
public class ViewSiteListController extends Controller {
    DisplaySiteListView displaySitesView;
    CollectionModel collectionModel;

    public ViewSiteListController(DisplaySiteListView displaySitesView, CollectionModel collectionModel) {
        this.displaySitesView = displaySitesView;
        this.collectionModel = collectionModel;
        collectionModel.updateCollection(Path.SITE.getPath());
        displaySitesView.update();
    }


}

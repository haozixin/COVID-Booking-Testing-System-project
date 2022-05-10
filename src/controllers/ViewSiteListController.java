package controllers;

import enums.Path;
import models.Collection;
import views.DisplaySiteListView;

public class ViewSiteListController extends Controller {
    DisplaySiteListView displaySitesView;
    Collection collectionModel;

    public ViewSiteListController(DisplaySiteListView displaySitesView, Collection collectionModel) {
        this.displaySitesView = displaySitesView;
        this.collectionModel = collectionModel;
        collectionModel.updateCollection(Path.SITE.getPath());
        displaySitesView.update();
    }


}

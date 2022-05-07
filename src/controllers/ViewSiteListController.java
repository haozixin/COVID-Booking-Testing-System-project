package controllers;

import models.Collection;
import views.DisplaySiteListView;

public class ViewSiteListController extends Controller {
    DisplaySiteListView displaySitesView;
    Collection collectionModel;

    public ViewSiteListController(DisplaySiteListView displaySitesView, Collection collectionModel) {
        this.displaySitesView = displaySitesView;
        this.collectionModel = collectionModel;
        collectionModel.getAllSites();
        displaySitesView.update();
    }


}

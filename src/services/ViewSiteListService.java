package services;


import controllers.ViewSiteListController;
import engine.Service;
import engine.CurrentOperator;
import models.CollectionModel;
import views.DisplaySiteListView;

/**
 * Action to view the list of testing sites.
 */
public class ViewSiteListService extends Service {

    @Override
    public String execute(CurrentOperator currentOperator) {
        CollectionModel collectionModel = new CollectionModel();
        DisplaySiteListView view = new DisplaySiteListView(collectionModel);
        controller = new ViewSiteListController(view, collectionModel);

        view.setVisible(true);
        return "";
    }

    @Override
    public String menuDescription(CurrentOperator currentOperator) {
        return "Go to view testing-sites list";
    }
}

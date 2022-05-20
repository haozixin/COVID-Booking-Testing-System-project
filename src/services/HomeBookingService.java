package services;

import controllers.HomeBookingController;
import engine.Service;
import enums.Path;
import engine.CurrentOperator;
import models.CollectionModel;
import models.HomeBookingModel;
import views.HomeBookingView;

/**
 * Action to book a Home-Testing
 */
public class HomeBookingService extends Service {
    @Override
    public String execute(CurrentOperator currentOperator) {
        CollectionModel collectionModel = new CollectionModel();
        collectionModel.updateCollection(Path.SITE.getPath());

        HomeBookingModel homeBookingModel = new HomeBookingModel();
        HomeBookingView view = new HomeBookingView(collectionModel, homeBookingModel);
        HomeBookingController controller = new HomeBookingController(view, homeBookingModel);
        view.setVisible(true);

        return "";
    }

    @Override
    public String menuDescription(CurrentOperator currentOperator) {
        return "Go to book a Home-Testing";
    }
}

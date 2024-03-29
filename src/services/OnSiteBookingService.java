package services;

import com.fasterxml.jackson.databind.node.ObjectNode;
import controllers.OnSiteBookingController;
import engine.CurrentOperator;
import engine.Service;
import enums.Path;
import models.*;
import models.bookings.OnsiteBookingModel;
import models.facilities.CovidTestingSiteModel;
import models.users.UserModel;
import views.OnSiteBookingView;

import java.util.ArrayList;

/**
 * Action to book the testing on site
 */
public class OnSiteBookingService extends Service {

    @Override
    public String execute(CurrentOperator currentOperator) {
        UserModel userModel = new UserModel();
        OnsiteBookingModel bookingModel = new OnsiteBookingModel();
        CovidTestingSiteModel covidTestingSiteModel = new CovidTestingSiteModel();

        CollectionModel collectionModel = new CollectionModel();
        collectionModel.updateCollection(Path.SITE.getPath());
        ArrayList<ObjectNode> siteList = collectionModel.filterByOnFactor(CovidTestingSiteModel.HAS_ON_SITE_BOOKING_FIELD, "true");
        if (siteList.size() > 0) {
            collectionModel.setCollection(siteList);
        } else {
            collectionModel.setCollection(new ArrayList<>());
        }
        OnSiteBookingView view = new OnSiteBookingView(collectionModel, bookingModel, covidTestingSiteModel);

        controller = new OnSiteBookingController(bookingModel, userModel, covidTestingSiteModel, view);
        view.setVisible(true);
        return "";
    }

    @Override
    public String menuDescription(CurrentOperator currentOperator) {
        return "Make an on-site booking";
    }
}

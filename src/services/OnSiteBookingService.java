package services;

import com.fasterxml.jackson.databind.node.ObjectNode;
import controllers.OnSiteBookingController;
import engine.Service;
import enums.Path;
import models.*;
import views.OnSiteBookingView;

import java.util.ArrayList;

public class OnSiteBookingService extends Service {

    @Override
    public String execute(Actor actor) {
        User userModel = new User();
        OnsiteBooking bookingModel = new OnsiteBooking();
        CovidTestingSite covidTestingSiteModel = new CovidTestingSite();

        Collection collection = new Collection();
        collection.updateCollection(Path.SITE.getPath());
        ArrayList<ObjectNode> siteList = collection.filterByOnFactor(CovidTestingSite.HAS_ON_SITE_BOOKING_FIELD, "true");
        if (siteList.size() > 0) {
            collection.setCollection(siteList);
        } else {
            collection.setCollection(new ArrayList<>());
        }
        OnSiteBookingView view = new OnSiteBookingView(collection, bookingModel, covidTestingSiteModel);

        controller = new OnSiteBookingController(bookingModel, userModel, covidTestingSiteModel, view);
        view.setVisible(true);
        return "";
    }

    @Override
    public String menuDescription(Actor actor) {
        return "Make an on-site booking";
    }
}

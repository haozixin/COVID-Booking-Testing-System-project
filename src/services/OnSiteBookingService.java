package services;

import com.fasterxml.jackson.databind.node.ObjectNode;
import controllers.CheckBookingController;
import controllers.OnSiteBookingController;
import engine.Service;
import enums.Path;
import models.*;
import views.CheckBookingView;
import views.OnSiteBookingView;

import java.util.ArrayList;

public class OnSiteBookingService extends Service {

    @Override
    public String execute(Actor actor) {
        User userModel = new User();
        OnsiteBooking bookingModel = new OnsiteBooking();
        Site siteModel = new Site();

        Collection collection = new Collection();
        collection.updateCollection(Path.SITE.getPath());
        ArrayList<ObjectNode> siteList = collection.filterByOnFactor(TestingSite.HAS_ON_SITE_BOOKING_FIELD, "true");
        if (siteList.size() > 0) {
            collection.setCollection(siteList);
        } else {
            collection.setCollection(new ArrayList<>());
        }
        OnSiteBookingView view = new OnSiteBookingView(collection, bookingModel, siteModel);

        controller = new OnSiteBookingController(bookingModel, userModel, siteModel, view);
        view.setVisible(true);
        return "";
    }

    @Override
    public String menuDescription(Actor actor) {
        return "Make an on-site booking";
    }
}

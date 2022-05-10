package services;

import controllers.CheckBookingController;
import controllers.SearchSiteController;
import engine.Service;
import models.Actor;
import models.Collection;
import views.CheckBookingView;
import views.SearchSitesView;

public class CheckBookingService extends Service {
    @Override
    public String execute(Actor actor) {
        Collection collection = new Collection();
        CheckBookingView view = new CheckBookingView(collection);
        controller = new CheckBookingController(view,collection);
        view.setVisible(true);
        return "";
    }

    @Override
    public String menuDescription(Actor actor) {
        return "Go to check booking by Pin code";
    }
}

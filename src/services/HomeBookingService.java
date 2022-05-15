package services;

import controllers.HomeBookingController;
import engine.Service;
import enums.Path;
import models.Actor;
import models.Collection;
import models.HomeBooking;
import views.HomeBookingView;

public class HomeBookingService extends Service {
    @Override
    public String execute(Actor actor) {
        Collection collection = new Collection();
        collection.updateCollection(Path.SITE.getPath());

        HomeBooking homeBookingModel = new HomeBooking();
        HomeBookingView view = new HomeBookingView(collection, homeBookingModel);
        HomeBookingController controller = new HomeBookingController(view, homeBookingModel);
        view.setVisible(true);

        return "";
    }

    @Override
    public String menuDescription(Actor actor) {
        return "Go to book a Home-Testing";
    }
}

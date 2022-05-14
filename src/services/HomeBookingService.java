package services;

import engine.Service;
import enums.Path;
import models.Actor;
import models.Collection;
import views.HomeBookingView;

public class HomeBookingService extends Service {
    @Override
    public String execute(Actor actor) {
        Collection collection = new Collection();
        collection.updateCollection(Path.SITE.getPath());
        HomeBookingView view = new HomeBookingView(collection);
        view.setVisible(true);

        return "";
    }

    @Override
    public String menuDescription(Actor actor) {
        return "Go to book a Home-Testing";
    }
}

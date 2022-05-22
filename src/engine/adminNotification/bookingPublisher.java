package engine.adminNotification;

import enums.Path;
import enums.UserRoles;
import models.CollectionModel;
import webServiceAPI.ServicesAdapter;
import webServiceAPI.WebServicesTarget;

import java.io.IOException;

public class bookingPublisher extends Publisher{
    public bookingPublisher() {

    }

    /**
     * Initalizing the subscribers - the observers
     * Get data from database and add observers that are users with admin role to the list
     */
    private void initializeSubscribers() {
//        WebServicesTarget wst = new ServicesAdapter();
//        try {
//            wst.getAllData(Path.USER.getPath(), null);
//        } catch (IOException | InterruptedException e) {
//            e.printStackTrace();
//        }
        // The collectionModel is charge for dealing with collection of data
        // It has some relative logic/functions for it, and can be reused in other classes when we need this kind of logic
        CollectionModel cm = new CollectionModel();
        cm.updateCollection(Path.USER.getPath());
        cm.filterByOnFactor(UserRoles.IS_ADMIN_FIELD.getName(), "true");
    }

    @Override
    public void notifyObservers(String message) {
        //TODO: implement this

    }
}

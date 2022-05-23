package engine.adminNotification;

import com.fasterxml.jackson.databind.node.ObjectNode;
import enums.Path;
import enums.UserRoles;
import models.CollectionModel;
import models.users.UserModel;
import webServiceAPI.ServicesAdapter;
import webServiceAPI.WebServicesTarget;

import java.io.IOException;
import java.util.ArrayList;

public class BookingPublisher extends Publisher{
    public static final String WORK_AT_SITE_FIELD = "workAt(siteId)";
    private static BookingPublisher instance;

    private BookingPublisher(){
        super();
        initializeSubscribers();
    }

    public static BookingPublisher getInstance(){
        if(instance == null){
            instance = new BookingPublisher();
        }
        return instance;
    }

    /**
     * Initalizing the subscribers - the observers
     * Get data from database and add observers that are users with admin role to the list
     */
    private void initializeSubscribers() {

        // The collectionModel is charge for dealing with collection of data
        // It has some relative logic/functions for it, and can be reused in other classes when we need this kind of logic
        CollectionModel cm = new CollectionModel();
        cm.updateCollection(Path.USER.getPath());
        ArrayList<ObjectNode> admins = cm.filterByOnFactor(UserRoles.IS_ADMIN_FIELD.getName(), "true");
        // see if the objectNode has the field "workAt(siteId)"
        // we have assumed that admin must have the field "workAt(siteId)" in additionalInfo field
        // but in case of unclean data, we just do a simple filter here - we only save the admin who has the field
        for (ObjectNode admin : admins) {
            if (admin.get("additionalInfo").has(WORK_AT_SITE_FIELD)) {
                join(new UserModel(admin));
            }
        }
    }

    @Override
    public void notifyObservers(String name, String facilityId, String message) {
        if (facilityId != null) {
            for (Subscriber subscriber : subscribers) {
                // if the subscriber is not the one who made the booking, then notify him
                if(!subscriber.getName().equalsIgnoreCase(name) && subscriber.getFacilityId().equals(facilityId)) {
                    subscriber.update(" I received a message from " + name+" -- "+message);
                    System.out.println("Have send the message to " + subscriber.getName() +" worked at the facility (id: "+facilityId+")");
                }
            }
        }else{
            // if the facilityId is null, we will send the notification to all the subscribers
            for (Subscriber subscriber : subscribers) {
                if(!subscriber.getName().equalsIgnoreCase(name)) {
                    subscriber.update("I received a message from " + name+", "+message);
                    System.out.println("Have send the message to " + subscriber.getName());
                }
            }
        }

    }

    @Override
    public void getNotification(String name) {
        for (Subscriber subscriber : subscribers) {
            if(subscriber.getName().equals(name)) {
                subscriber.receiveMessage();
            }

        }
    }


}

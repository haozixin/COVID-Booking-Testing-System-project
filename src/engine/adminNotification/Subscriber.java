package engine.adminNotification;

import java.util.ArrayList;

/**
 * Interface for a subscriber to the admin notification system.
 *
 */
public interface Subscriber {
    void update(String message);
    String getName();

    /**
     * Broadcast the message to all the subscribers(except the sender)
     * @param publisher - the publisher of the message
     * @param message - the message to be broadcasted
     * @param facilityId - the facility id of the publisher
     *                   if the id is null, then broadcast to all the subscribers
     *                   if the id is not null, then broadcast to all the subscribers who work on the specified facility
     */
    void broadCast(Publisher publisher, String message, String facilityId);

    /**
     * since our application is console based, we have to implement this method - receiver
     * receives the message
     */
    ArrayList<String> receiveMessage();

    String getFacilityId();
}

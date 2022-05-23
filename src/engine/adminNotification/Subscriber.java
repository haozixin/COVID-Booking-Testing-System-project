package engine.adminNotification;

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
     */
    void broadCast(Publisher publisher, String message);

    /**
     * since our application is console based, we have to implement this method - receiver
     * receives the message
     */
    String receiveMessage();

    String getFacilityId();
}

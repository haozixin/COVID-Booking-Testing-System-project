package engine.adminNotification;

import java.util.ArrayList;

/**
 * Publishers class contain a subscription infrastructure.
 * To let new subscribers join and current subscribers leave the list.
 */
public abstract class Publisher {
    // The list of subscribers - members of one CovidTesting site
    protected ArrayList<Subscriber> subscribers = new ArrayList<>();

    public void join(Subscriber subscriber) {
        if (!subscribers.contains(subscriber)) {
            subscribers.add(subscriber);
            System.out.println(subscriber.getName() + " joined the " + this.getClass().getSimpleName() + ".");
        }
    }

    public void leave(Subscriber subscriber) {
        subscribers.remove(subscriber);
        System.out.println(subscriber.getName() + " left the " + this.getClass().getSimpleName() + ".");
    }

    /**
     * This function is used to publish the message to all subscribers (except the publisher)
     * @param name the name of the publisher (userName)
     * @param facilityId the facilityId of the publisher ("workAt(siteId)" field in additionalInfo field of user)
     *                   if facilityId is null, then the publisher will send the message to all subscribers
     */
    public abstract void notifyObservers(String name, String facilityId, String message);

    /**
     * Since our application is console-based
     * We need this function as a helper to get the message that published to the subscribers
     * @param userName the message that published to the subscribers
     */
    public abstract void getNotification(String userName);




}

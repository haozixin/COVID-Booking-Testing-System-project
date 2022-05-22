package engine.adminNotification;

import java.util.ArrayList;

public abstract class Publisher {
    // The list of subscribers - members of one CovidTesting site
    protected ArrayList<Subscriber> subscribers = new ArrayList<>();

    public void join(Subscriber subscriber) {
        subscribers.add(subscriber);
        System.out.println(subscriber.getName() + " joined the " + this.getClass().getSimpleName() + ".");
    }

    public void leave(Subscriber subscriber) {
        subscribers.remove(subscriber);
        System.out.println(subscriber.getName() + " left the " + this.getClass().getSimpleName() + ".");
    }

    public abstract void notifyObservers(String message);




}

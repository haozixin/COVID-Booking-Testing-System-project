package engine;

import java.io.IOException;
import java.util.ArrayList;

/**
 * This class is used to publish data to the DataCollector.
 * when the data in web side is changed, the publisher need to be called to update data.
 */
public class DataPublisher {
    private ArrayList<DataSubscriber> subscribers;

    /**
     * Constructor
     */
    public DataPublisher() {
        this.subscribers = new ArrayList<>();
    }

    public void subscribe(DataSubscriber subscriber) {
        subscribers.add(subscriber);
    }

    public void unsubscribe(DataSubscriber subscriber) {
        subscribers.remove(subscriber);
    }

    /**
     * This method is used to publish data to the DataCollector.
     * @throws IOException
     * @throws InterruptedException
     */
    public void notifySubscribers() throws IOException, InterruptedException {
        for (DataSubscriber subscriber : subscribers) {
            subscriber.update();
        }
    }



}

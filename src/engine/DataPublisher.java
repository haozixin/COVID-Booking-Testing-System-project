package engine;

import java.io.IOException;
import java.util.ArrayList;

public class DataPublisher {
    private ArrayList<DataSubscriber> subscribers;
    private boolean mainState;

    public DataPublisher() {
        this.subscribers = new ArrayList<>();
    }

    public void subscribe(DataSubscriber subscriber) {
        subscribers.add(subscriber);
    }

    public void unsubscribe(DataSubscriber subscriber) {
        subscribers.remove(subscriber);
    }

//    public void notifySubscribers(boolean state) {
//        mainState = state;
//        for (DataSubscriber subscriber : subscribers) {
//            subscriber.update(state);
//        }
//    }

    public void notifySubscribers() throws IOException, InterruptedException {
        for (DataSubscriber subscriber : subscribers) {
            subscriber.update();
        }
    }

    public boolean getMainState() {
        return mainState;
    }

    public void setMainState(boolean mainState) {
        this.mainState = mainState;
    }


}

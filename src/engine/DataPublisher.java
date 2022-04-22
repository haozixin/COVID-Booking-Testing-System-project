package engine;

import java.util.ArrayList;

public class DataPublisher {
    private ArrayList<DataSubscriber> subscribers;
    private boolean mainState;

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

    public boolean getMainState() {
        return mainState;
    }

    public void setMainState(boolean mainState) {
        this.mainState = mainState;
    }


}

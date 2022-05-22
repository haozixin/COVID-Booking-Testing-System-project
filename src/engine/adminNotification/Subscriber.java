package engine.adminNotification;

public interface Subscriber {
    void update(String message);
    String getName();
}

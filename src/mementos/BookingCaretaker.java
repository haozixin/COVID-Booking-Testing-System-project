package mementos;

/**
 * Subclass of Caretaker.
 * We need singleton pattern here to make sure our history will not disappear.
 * As long as the application is running(no matter which subsystem is stopped),
 * the Caretaker is the only one who can manage the history and not be discarded by the subsystem/java VM,
 * so that users can do the undo operation at any time.
 */
public class BookingCaretaker extends Caretaker {

    private static BookingCaretaker instance;

    private BookingCaretaker() {
    }

    public static BookingCaretaker getInstance() {
        if (instance == null) {
            instance = new BookingCaretaker();
        }
        return instance;
    }
}

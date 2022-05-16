package mementos;

import com.fasterxml.jackson.databind.node.ObjectNode;
import models.BookingModel;

import java.util.Date;

/**
 * Concrete Memento class
 */
public class BookingMemento implements IMemento{
    /**
     * meta data for this memento - Date
     */
    private Date createDate;

    private ObjectNode state;

    // from outside (passed in as parameter)
    private BookingModel originator;

    /**
     * Constructor
     * @param originator
     */
    public BookingMemento(BookingModel originator) {
        this.originator = originator;
        this.state = originator.getState();
    }


    @Override
    public void restore() {
        // the state will change if we make some changes outside, but the originator is the same one
        // in different mementos, so the restore function will set the originator back to previous state if some changes happen outside
        // "Outside" means (beyond the memento) (data not stored into memento)
        originator.setState(state);
    }
}

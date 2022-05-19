package mementos;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import enums.Path;
import models.BookingModel;

import javax.swing.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Concrete Memento class
 */
public class BookingMemento implements IMemento {
    /**
     * meta data for this memento - Date
     */
    private Date createDate;
    private String bookingId;
    private String venueId;
    private String startTime;

    private ObjectNode state;

    // the originator from outside (passed in as parameter)
    private BookingModel originator;

    /**
     * Constructor
     *
     * @param originator
     */
    public BookingMemento(BookingModel originator) {
        this.originator = originator;
        this.state = originator.getState();
        this.createDate = new Date();
        this.bookingId = originator.getEntityId();
        this.venueId = originator.getVenueId();
        this.startTime = originator.getStartTime();
    }


    @Override
    public void restore() {
        // the state will change if we make some changes outside, but the originator is the same one
        // in different mementos, so the restore function will set the originator back to previous state if some changes happen outside
        // "Outside" means (beyond the memento) (data not stored into memento)
        originator.setState(state);
        originator.updateRemoteData();
    }

    public String getMetaData() {
        return  "HISTORY: \n" +
                "CREATED AT: " + createDate + "\t \n" +
                "BOOKING_ID: " + bookingId + " \t\n" +
                "VENUE_ID: " + venueId + " \t\n" +
                "START_TIME: " + startTime;
    }

    public boolean isValid() {
        Date validTime = null;
        try {
            validTime = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").parse(startTime);
            if (validTime.before(new Date())) {
                return false;
            }
            else{
                return true;
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return false;
    }
}

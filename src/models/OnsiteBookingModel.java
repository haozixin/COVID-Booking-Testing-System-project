package models;


import com.fasterxml.jackson.databind.node.ObjectNode;
import mementos.BookingMemento;
import mementos.IMemento;

public class OnsiteBookingModel extends BookingModel {


    public static final String ONSITE = "onsite";

    public void setSchema(String customerId, String testingSiteId, String dateTime) {
        super.setSchema(customerId, testingSiteId);
        additionalInfo.put(BOOKING_TYPE_FIELD, ONSITE);
        entityInfo.put(START_TIME_FIELD, dateTime);

        // add additionalInfo to the entityInfo
        entityInfo.putPOJO(ADDITIONAL_INFO_FIELD, additionalInfo);
    }

    public OnsiteBookingModel() {
    }

    public OnsiteBookingModel(ObjectNode data) {
        super(data);
    }

    @Override
    public IMemento save() {
        return new BookingMemento(this);
    }

    @Override
    public ObjectNode getState() {
        return entityInfo;
    }

    @Override
    public boolean canBeChanged(String id) {
        boolean isValid = super.canBeChanged(id);
        // if the booking is not onsite booking, we cannot change it
        boolean isOnsite = entityInfo.findValue(BOOKING_TYPE_FIELD).asText().equals(ONSITE);
        return isValid && isOnsite;
    }
}

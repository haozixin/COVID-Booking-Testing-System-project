package models;


import com.fasterxml.jackson.databind.node.ObjectNode;
import mementos.BookingMemento;
import mementos.IMemento;

public class OnsiteBookingModel extends BookingModel {


    public void setSchema(String customerId, String testingSiteId) {
        super.setSchema(customerId, testingSiteId);

        // add additionalInfo to the entityInfo
        entityInfo.putPOJO(ADDITIONAL_INFO_FIELD, additionalInfo);
    }


    @Override
    public IMemento save() {
        return new BookingMemento(this);
    }

    @Override
    public ObjectNode getState() {
        return entityInfo;
    }
}

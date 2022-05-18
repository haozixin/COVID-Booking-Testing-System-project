package models;

import com.fasterxml.jackson.databind.node.ObjectNode;
import mementos.BookingMemento;
import mementos.IMemento;

/**
 * HomeBooking class is a subclass of Booking class.
 */
public class HomeBookingModel extends BookingModel {


    public static final String HOME_TYPE = "home";

    public void setSchema(String customerId, String testingSiteId, boolean hasRATKit) {
        super.setSchema(customerId, testingSiteId);

        String url = generateURL();
        String QRCode = generateQRCode();
        additionalInfo.put(QR_CODE_FIELD, QRCode);
        additionalInfo.put(URL_FIELD, url);
        additionalInfo.put(HAS_RAT_KIT_FIELD, hasRATKit);
        additionalInfo.put(BOOKING_TYPE_FIELD, HOME_TYPE);
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

package models;

/**
 * HomeBooking class is a subclass of Booking class.
 */
public class HomeBookingModel extends BookingModel {


    public void setSchema(String customerId, String testingSiteId, boolean hasRATKit) {
        super.setSchema(customerId, testingSiteId);
        additionalInfo.put(HAS_RAT_KIT_FIELD, hasRATKit);
        // add additionalInfo to the entityInfo
        entityInfo.putPOJO(ADDITIONAL_INFO_FIELD, additionalInfo);

    }



}

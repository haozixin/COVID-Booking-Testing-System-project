package models;


public class OnsiteBooking extends Booking {


    public void setSchema(String customerId, String testingSiteId) {
        super.setSchema(customerId, testingSiteId);

        // add additionalInfo to the entityInfo
        entityInfo.putPOJO(ADDITIONAL_INFO_FIELD, additionalInfo);
    }



}

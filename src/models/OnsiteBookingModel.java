package models;


public class OnsiteBookingModel extends BookingModel {


    public void setSchema(String customerId, String testingSiteId) {
        super.setSchema(customerId, testingSiteId);

        // add additionalInfo to the entityInfo
        entityInfo.putPOJO(ADDITIONAL_INFO_FIELD, additionalInfo);
    }



}

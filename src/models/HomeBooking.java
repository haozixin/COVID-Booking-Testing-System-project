package models;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * HomeBooking class is a subclass of Booking class.
 */
public class HomeBooking extends Booking {


    public void setSchema(String customerId, String testingSiteId, boolean hasRATKit) {
        super.setSchema(customerId, testingSiteId);
        additionalInfo.put(HAS_RAT_KIT_FIELD, hasRATKit);
        // add additionalInfo to the entityInfo
        entityInfo.putPOJO(ADDITIONAL_INFO_FIELD, additionalInfo);

    }



}

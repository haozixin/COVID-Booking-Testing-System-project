package bookingSubsystem;

import com.fasterxml.jackson.databind.node.ObjectNode;

public class Booking {
    public static final String SMS_PIN_FIELD = "smsPin";
    public static final String STATUS_FIELD = "status";
    private ObjectNode bookingInfo;

    public Booking(ObjectNode bookingJsonNode){
        this.bookingInfo = bookingJsonNode;
    }

    /**
     * extract the pincode of the booking
     * @return String pincode
     */
    public String getPinCode(){
        System.out.println(bookingInfo.get(SMS_PIN_FIELD).toString());
        return bookingInfo.get(SMS_PIN_FIELD).toString();
    }

    /**
     * get the status
     * @return
     */
    public String getStatus(){
        System.out.println(bookingInfo.get(STATUS_FIELD).toString());
        return bookingInfo.get(STATUS_FIELD).toString();
    }
}

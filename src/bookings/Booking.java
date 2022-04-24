package bookings;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import testingSites.TestingSite;

import users.User;
import utility.Utility;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;

public class Booking {

    public static final String name = "Booking";

    public static final String CUSTOMER_FIELD = "customer";
    public static final String TESTING_SITE_FIELD = "testingSites";
    public static final String USER_ID_FIELD = "customerId";
    public static final String TESTING_SITE_ID_FIELD = "testingSiteId";

    public static final String SMS_PIN_FIELD = "smsPin";
    public static final String STATUS_FIELD = "status";
    public static final String START_TIME_FIELD = "startTime";
    public static final String NOTES_FIELD = "notes";
    public static final String ADDITIONAL_INFO_FIELD = "additionalInfo";
    public static final String NEED_QR_CODE_FIELD = "needQRCode";
    private ObjectNode bookingInfo;
    private ObjectNode additionalInfo = new ObjectMapper().createObjectNode();


    /**
     * Constructor 1: used when the booking is created by the system
     *
     * @param bookingJsonNode
     */
    public Booking(ObjectNode bookingJsonNode) {
        this.bookingInfo = bookingJsonNode;
        try {
            additionalInfo = additionalInfo.get(ADDITIONAL_INFO_FIELD).deepCopy();
        } catch (NullPointerException e) {
        }

    }



    /**
     * Constructor 2: used when the booking is created by the users
     *
     * @throws JsonProcessingException
     */
    public Booking(String customerId, String testingSiteId) throws JsonProcessingException {
        // initial attributes Template(Schema)
        initialSchema();
        // get data from users (input)
        buildRequestBody(customerId, testingSiteId);
    }

    /**
     * extract the pincode of the booking
     *
     * @return String pincode
     */
    public String getPinCode() {
        return bookingInfo.get(SMS_PIN_FIELD).asText();
    }

    @Override
    public String toString() {
        return bookingInfo.toString();
    }

    public String display() {
        return Utility.displayMessage(name, bookingInfo);
    }


    /**
     * Build a request body (For Post request)
     * Go through the template(created by initialSchema()) and get the value of each attribute from user input
     * <p>
     * It can avoid errors that caused by the missing required attributes.
     * and we don't need to change every outside places if there are more or less fields required on Web side)
     *
     * @param customerId
     * @param testingSiteId
     */
    private void buildRequestBody(String customerId, String testingSiteId) {

        // 1. update data in schema using user's input
        for (Iterator<String> it = bookingInfo.fieldNames(); it.hasNext(); ) {
            String key = it.next();
            // only set those data -- is the type of String
            if (bookingInfo.get(key).isTextual()) {
                System.out.print(key + " : ");
                Scanner s = new Scanner(System.in);
                String value = s.next();
                bookingInfo.put(key, value);
            }
        }

        // 2. set the customerId, testingSiteId, and startTime; they are required fields but don't need to be input by user
        bookingInfo.put(USER_ID_FIELD, customerId);
        bookingInfo.put(TESTING_SITE_ID_FIELD, testingSiteId);
        String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(System.currentTimeMillis()));
        bookingInfo.put(START_TIME_FIELD, time);
    }

    /**
     * Add the required attributes to the bookingInfo
     * before let the user to create a new booking, we need to set a template(which attributes are required) for the booking
     * When user create a new object of booking, the attributes will be reset based on the template
     * So, if there are more attributes required, we can add them here
     */
    private void initialSchema() {
        bookingInfo = new ObjectMapper().createObjectNode();
        bookingInfo.put(NOTES_FIELD, "String");
        // if user DOESN'T need to use QR code, we just set the field to false.
        setNeedQRCode(true);
    }

    /**
     * the function could be used when users need to mark that customers don't need QR code to be scanned(since they already have RAT kit)
     */
    public void setNeedQRCode(boolean ifNeedQRCode) {

        additionalInfo.put(NEED_QR_CODE_FIELD, ifNeedQRCode);
        bookingInfo.putPOJO(ADDITIONAL_INFO_FIELD, additionalInfo);
    }

    private void generateURL() {
        //TODO: 什么算dummy url; 怎么写。。需要问
    }

    private void generateQRCode() {
        //TODO: 什么算dummy QRcode; 怎么写。。需要问
    }


}

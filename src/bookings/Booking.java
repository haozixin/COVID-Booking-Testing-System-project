package bookings;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import testingSites.TestingSite;

import users.User;
import utility.Utility;

import java.util.Iterator;
import java.util.Scanner;

public class Booking {

    private User user;
    private TestingSite testingSite;
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
    private ObjectNode bookingInfo;

    /**
     * Constructor 1: used when the booking is created by the system
     * @param bookingJsonNode
     */
    public Booking(ObjectNode bookingJsonNode){
        this.bookingInfo = bookingJsonNode;
        setCustomer();
        setTestingSite();
    }

    private void setCustomer(){
        JsonNode customerJsonNode = bookingInfo.get(CUSTOMER_FIELD);
        user = new User((ObjectNode) customerJsonNode);
    }

    private void setTestingSite(){
        JsonNode testingSiteJsonNode = bookingInfo.get(TESTING_SITE_FIELD);
        testingSite = new TestingSite((ObjectNode) testingSiteJsonNode);
    }

    /**
     * Constructor 2: used when the booking is created by the users
     * @throws JsonProcessingException
     */
    public Booking() throws JsonProcessingException {
        // initial attributes Template(Schema)
        initialSchema();
        // get data from users (input)
        buildRequestBody();
    }

    /**
     * extract the pincode of the booking
     * @return String pincode
     */
    public String getPinCode(){
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
     * (creating new entity by actor will be done by this method(collect user's input). It can avoid errors because of the missing required attributes.
     * and we don't need to change every outside places if there are more or less fields required on Web side)
     */
    private void buildRequestBody() {
        // 1. the role of user will be set to true when the instance of the class is created

        // 2. update other data from user's input
        for (Iterator<String> it = bookingInfo.fieldNames(); it.hasNext(); ) {
            String key = it.next();
            // the value of the key is the type of String
            if (bookingInfo.get(key).isTextual()) {
                System.out.print(key + " : ");
                Scanner s = new Scanner(System.in);
                String value = s.next();
                bookingInfo.put(key, value);
            }
        }
    }

    /**
     * before let the user to create a new booking, we need to set a template(which attributes are required) for the booking
     * When use create a new object of booking, the attributes will be reset based on the template
     */
    private void initialSchema(){
        bookingInfo = new ObjectMapper().createObjectNode();
        bookingInfo.put(USER_ID_FIELD, "String");
        bookingInfo.put(TESTING_SITE_ID_FIELD, "String");
        bookingInfo.put(START_TIME_FIELD, "String");
        bookingInfo.put(NOTES_FIELD, "String");
        // if user DOESN'T need to use QR code, we just set the field to false.
        bookingInfo.putObject(ADDITIONAL_INFO_FIELD).put("needQRCode", true);
    }

    /**
     * the function could be used when users need to mark that customers don't need QR code to be scanned(since they already have RAT kit)
     */
    public void setNeedQRCode(){
        bookingInfo.putObject(ADDITIONAL_INFO_FIELD).put("needQRCode", false);
    }

    private void generateURL(){
      //TODO: 什么算dummy url; 怎么写。。需要问
    }

    private void generateQRCode(){
        //TODO: 什么算dummy QRcode; 怎么写。。需要问
    }



}

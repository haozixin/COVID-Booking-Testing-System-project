package bookings;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import engine.Entity;
import utility.Utility;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;

public class Booking extends Entity {


    public static final String className = "Booking";

    public static final String USER_ID_FIELD = "customerId";
    public static final String TESTING_SITE_ID_FIELD = "testingSiteId";

    public static final String SMS_PIN_FIELD = "smsPin";
    public static final String ID_FIELD = "id";
    public static final String STATUS_FIELD = "status";
    public static final String START_TIME_FIELD = "startTime";
    public static final String NOTES_FIELD = "notes";
    public static final String ADDITIONAL_INFO_FIELD = "additionalInfo";
    public static final String QR_CODE_FIELD = "QRCode";
    public static final String URL_FIELD = "url";
    public static final String HAS_RAT_KIT_FIELD = "hasRATKit";


    /**
     * Constructor 1: used when the booking is created by the system
     *
     * @param bookingJsonNode
     */
    public Booking(ObjectNode bookingJsonNode) {
        super(bookingJsonNode);
    }



    /**
     * Constructor 2: used when the booking is created by the users
     *
     */
    private Booking(String customerId, String testingSiteId){
        // initial attributes Template(Schema)
        initialSchema();
        // get data from users (input)
        addValueToSchema();

        entityInfo.put(USER_ID_FIELD, customerId);
        entityInfo.put(TESTING_SITE_ID_FIELD, testingSiteId);
        String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(System.currentTimeMillis()));
        entityInfo.put(START_TIME_FIELD, time);

    }

    /**
     * extract the pincode of the booking
     *
     * @return String pincode
     */
    public String getPinCode() {
        return entityInfo.get(SMS_PIN_FIELD).asText();
    }

    public String display() {
        return Utility.formatMessage(className, entityInfo);
    }

    public static String createNewEntity(String customerId, String testingSiteId){

        Booking booking = new Booking(customerId, testingSiteId);
        return booking.getEntityInfo();

    }

    @Override
    protected void initialSchema() {
        entityInfo = new ObjectMapper().createObjectNode();
        entityInfo.put(NOTES_FIELD, "String");
    }

    /**
     * the function could be used when users need to mark that customers don't need QR code to be scanned(since they already have RAT kit)
     */
    public void updateRATKitInfo(String QRCode, String url, boolean hasRATKit) {

        additionalInfo.put(QR_CODE_FIELD, QRCode);
        additionalInfo.put(URL_FIELD, url);
        additionalInfo.put(HAS_RAT_KIT_FIELD, false);
        entityInfo.putPOJO(ADDITIONAL_INFO_FIELD, additionalInfo);
    }

    private String generateQRCode() {
        //Unique QR Code
        String QRCode = "Dummy QR Code(" + getEntityId()+")";
        return QRCode;
    }

    private String generateURL() {
        //Unique url
        String url = "https://" + getEntityId() +"/Dummy URL";
        return url;
    }

}

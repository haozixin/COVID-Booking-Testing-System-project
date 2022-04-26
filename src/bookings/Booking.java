package bookings;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import engine.Entity;
import enums.Path;
import utility.Utility;
import webServiceAPI.ServicesAdapter;
import webServiceAPI.WebServicesTarget;

import java.io.IOException;

/**
 * Booking class
 * store and create booking
 */
public abstract class Booking extends Entity {


    public static final String className = "Booking";

    protected static final String USER_ID_FIELD = "customerId";
    protected static final String TESTING_SITE_ID_FIELD = "testingSiteId";

    protected static final String SMS_PIN_FIELD = "smsPin";
    protected static final String START_TIME_FIELD = "startTime";
    protected static final String NOTES_FIELD = "notes";
    protected static final String ADDITIONAL_INFO_FIELD = "additionalInfo";
    protected static final String QR_CODE_FIELD = "QRCode";
    protected static final String URL_FIELD = "url";
    protected static final String HAS_RAT_KIT_FIELD = "hasRATKit";


    /**
     * Constructor 1: used when the booking is created by the system
     *
     * @param bookingJsonNode the json node of the booking
     */
    public Booking(ObjectNode bookingJsonNode) {
        super(bookingJsonNode);
    }

    public Booking() {
    }


    /**
     * extract the pincode of the booking
     *
     * @return String pincode
     */
    public String getPinCode() {
        return entityInfo.get(SMS_PIN_FIELD).asText();
    }

    public void display() {
        System.out.println(Utility.formatMessage(className, entityInfo));

    }


    @Override
    protected void initialSchema() {
        entityInfo = new ObjectMapper().createObjectNode();
        entityInfo.put(NOTES_FIELD, "String");
    }


    /**
     * the function could be used when users need to mark that customers don't need QR code to be scanned(since they already have RAT kit)
     */
    public boolean updateRATKitInfo(boolean hasRATKit) throws IOException, InterruptedException {
        additionalInfo.put(HAS_RAT_KIT_FIELD, hasRATKit);
        entityInfo.putPOJO(ADDITIONAL_INFO_FIELD, additionalInfo);

        WebServicesTarget webServicesTarget = new ServicesAdapter();
        boolean result = webServicesTarget.patchData(Path.BOOKING.getPath(), additionalInfo.toString(), getEntityId());
        if (result) {
            BookingsCollection.getInstance().update();

        }
        return result;
    }

    protected String generateQRCode() {
        //Unique QR Code
        String QRCode = "Dummy QR Code for ( " + getEntityId() + " )";
        return QRCode;
    }

    protected String generateURL() {
        //Unique url
        String url = "https://" + getEntityId() + "/Dummy URL";
        return url;
    }

    public String getURL() {
        try {
            return entityInfo.findValue(URL_FIELD).asText();
        } catch (NullPointerException e) {
            return null;
        }
    }

    public String getQRCode() {
        try {
            return entityInfo.findValue(QR_CODE_FIELD).asText();
        } catch (NullPointerException e) {
            return null;
        }
    }



}

package models;

import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class Booking extends Model {


    public static final String USER_ID_FIELD = "customerId";
    public static final String TESTING_SITE_ID_FIELD = "testingSiteId";

    public static final String SMS_PIN_FIELD = "smsPin";
    public static final String START_TIME_FIELD = "startTime";
    public static final String NOTES_FIELD = "notes";
    public static final String ADDITIONAL_INFO_FIELD = "additionalInfo";
    public static final String QR_CODE_FIELD = "QRCode";
    public static final String URL_FIELD = "url";
    public static final String HAS_RAT_KIT_FIELD = "hasRATKit";

    protected void initialSchema() {

    }
}

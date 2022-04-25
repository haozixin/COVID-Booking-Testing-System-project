package bookings;

import com.fasterxml.jackson.databind.node.ObjectNode;

import java.text.SimpleDateFormat;
import java.util.Date;

public class HomeBooking extends Booking {
    /**
     * Constructor 1: used when the booking is created by the system
     *
     * @param bookingJsonNode the booking json node
     */
    public HomeBooking(ObjectNode bookingJsonNode) {
        super(bookingJsonNode);
    }


    public static String createHomeBooking(String customerId, String testingSiteId, boolean hasRATKit) {

        Booking booking = new HomeBooking(customerId, testingSiteId, hasRATKit);

        return booking.getEntityInfo();

    }

    /**
     * Constructor 3: used when the booking is created by the users
     *
     */
    private HomeBooking(String customerId, String testingSiteId, boolean hasRATKit) {
        // initial attributes Template(Schema)
        initialSchema();
        // get data from users (input)
        addValueToSchema();

        entityInfo.put(USER_ID_FIELD, customerId);
        entityInfo.put(TESTING_SITE_ID_FIELD, testingSiteId);
        String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(System.currentTimeMillis()));
        entityInfo.put(START_TIME_FIELD, time);

        String url = generateURL();
        String QRCode = generateQRCode();
        additionalInfo.put(QR_CODE_FIELD, QRCode);
        additionalInfo.put(URL_FIELD, url);
        additionalInfo.put(HAS_RAT_KIT_FIELD, hasRATKit);
        entityInfo.putPOJO(ADDITIONAL_INFO_FIELD, additionalInfo);
    }

    @Override
    protected void initialSchema() {
        super.initialSchema();

    }
}

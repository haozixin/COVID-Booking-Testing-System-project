package bookings;

import com.fasterxml.jackson.databind.node.ObjectNode;

import java.text.SimpleDateFormat;
import java.util.Date;

public class OnsiteBooking extends Booking {
    /**
     * Constructor 1: used when the booking is created by the system
     *
     * @param bookingJsonNode
     */
    public OnsiteBooking(ObjectNode bookingJsonNode) {
        super(bookingJsonNode);
    }

    public static String createOnsiteBooking(String customerId, String testingSiteId) {

        Booking booking = new OnsiteBooking(customerId, testingSiteId);

        return booking.getEntityInfo();

    }
    /**
     * Constructor 2: used when the booking is created by the users
     *
     */
    private OnsiteBooking(String customerId, String testingSiteId){
        // initial attributes Template(Schema)
        initialSchema();
        // get data from users (input)
        addValueToSchema();

        entityInfo.put(USER_ID_FIELD, customerId);
        entityInfo.put(TESTING_SITE_ID_FIELD, testingSiteId);
        String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(System.currentTimeMillis()));
        entityInfo.put(START_TIME_FIELD, time);
    }

    @Override
    protected void initialSchema() {
        super.initialSchema();

    }
}

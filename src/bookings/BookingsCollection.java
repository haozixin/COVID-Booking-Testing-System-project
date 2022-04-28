package bookings;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import engine.DataCollection;
import engine.DataSubscriber;
import engine.Entity;
import enums.Path;
import testingSites.SitesCollection;
import testingSites.TestingSite;
import webServiceAPI.ServicesAdapter;
import webServiceAPI.WebServicesTarget;

import java.io.IOException;
import java.util.ArrayList;

/**
 *  This class is used to store all the bookings.
 */
public class BookingsCollection implements DataSubscriber, DataCollection {

    public static final String IS_HOME_BOOKING_FIELD = "isHomeBooking";
    private final ArrayList<Booking> bookings = new ArrayList<>();
    private static BookingsCollection instance = null;
    public static final String HOME_BOOKING_TYPE = "Home Booking";
    public static final String ONSITE_BOOKING_TYPE = "On Site Booking";

    private BookingsCollection() {
    }

    public static BookingsCollection getInstance() {
        if (instance == null) {
            instance = new BookingsCollection();
        }
        return instance;
    }

    private void addBooking(Booking booking) {
        bookings.add(booking);
    }

    private void clearBookings() {
        bookings.clear();
    }

    @Override
    public void update() throws IOException, InterruptedException {
        WebServicesTarget ws = new ServicesAdapter();
        ObjectNode[] objectNode = ws.getAllData(Path.BOOKING.getPath(), null);
        clearBookings();

        for (ObjectNode node : objectNode) {
            boolean isHome;
            try{
                isHome = node.findValue(IS_HOME_BOOKING_FIELD).asBoolean();
            }catch (NullPointerException e){
                isHome = false;
            }

            if (isHome) {
                Booking booking = new HomeBooking(node);
                addBooking(booking);
            }
            else {
                Booking booking = new OnsiteBooking(node);
                addBooking(booking);
            }
        }
    }

    @Override
    public Entity findEntity(String factor) {
        return null;
    }

    @Override
    public JsonNode searchForEntity(String factor, String query) throws IOException, InterruptedException {
        return null;
    }

    @Override
    public void printWholeList() {
        for (Booking booking : bookings) {
            System.out.println(booking);
        }
    }

    @Override
    public ArrayList<TestingSite> filterByOnFactor(String field, String value) {
        return null;
    }

    /**
     * find the status from the PIN Code the user input
     *
     * @return String status
     * @paramn String pincode to find the related status
     */
    @Override
    public Booking getStatusByPin(String pinCode) {
        for (Booking booking : bookings) {
            String temp = booking.getPinCode();
            if (temp.equals(pinCode)) {
                return booking;
            }
        }
        return null;
    }

    @Override
    public boolean updateHasRATKit(String QRcode, boolean hasRATKit) throws IOException, InterruptedException {
        for (Booking booking : bookings) {
            if(booking.getQRCode()!=null){
                if (booking.findValue(Booking.QR_CODE_FIELD).equals(QRcode)) {
                    return booking.updateRATKitInfo(hasRATKit);
                }
            }
        }
        return false;
    }

}

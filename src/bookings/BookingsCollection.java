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

    /**
     *  Create a home-booking for a user
     *  1. fill all necessary information and create a booking
     *  2. post to server
     *  3. update waitingTime
     *  4. update this collection to get new data
     * @param type     booking type - on-site or home
     * @param customerId the customer id
     * @param siteId     the site id
     * @return  the booking
     * @throws IOException
     * @throws InterruptedException
     */
    @Override
    public Booking createHomeBooking(String type, String customerId, String siteId, boolean hasRATKit) throws IOException, InterruptedException {
        WebServicesTarget webService = new ServicesAdapter();
        if(type.equals(HOME_BOOKING_TYPE)){
            if (customerId != null && siteId != null) {
                String jsonNode = HomeBooking.createHomeBooking(customerId, siteId, hasRATKit);
                ObjectNode response = webService.postData(Path.BOOKING.getPath(), jsonNode);
                Booking tempBooking = new HomeBooking(response);
                // we assume that the HomeBooking won't consuming waiting time

                update();
                return tempBooking;
            }
        }
        return null;

    }

    /**
     *  Create an Onsite-booking for a user
     *  1. fill all necessary information and create a booking
     *  2. post to server
     *  3. update waitingTime
     *  4. update this collection to get new data
     * @param type     booking type - on-site or home
     * @param customerId the customer id
     * @param siteId     the site id
     * @return  the booking
     * @throws IOException
     * @throws InterruptedException
     */
    @Override
    public String createOnsiteBooking(String type, String customerId, String siteId) throws IOException, InterruptedException {
        WebServicesTarget webService = new ServicesAdapter();
        SitesCollection sites = SitesCollection.getInstance();
        if (type.equals(ONSITE_BOOKING_TYPE)) {
            if (customerId != null && siteId != null) {
                String jsonNode = OnsiteBooking.createOnsiteBooking(customerId, siteId);
                ObjectNode response = webService.postData(Path.BOOKING.getPath(), jsonNode);
                Booking tempBooking = new OnsiteBooking(response);

                sites.updateWaitingTime(siteId);
                update();
                return tempBooking.getPinCode();
            }
        }

        return null;

    }







}

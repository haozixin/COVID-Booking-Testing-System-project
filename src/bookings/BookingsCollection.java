package bookings;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import engine.DataCollection;
import engine.DataSubscriber;
import engine.Entity;
import enums.Path;
import enums.Query;
import testingSites.SitesCollection;
import testingSites.TestingSite;
import users.User;
import users.UserCollection;
import webServiceAPI.ServicesAdapter;
import webServiceAPI.WebServicesTarget;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class BookingsCollection implements DataSubscriber, DataCollection {

    private final ArrayList<Booking> bookings = new ArrayList<>();
    private static BookingsCollection instance = null;

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
            Booking booking = new Booking(node);
            addBooking(booking);
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
    public void printList() {
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
     *  Create a booking for a user
     *  1. fill all necessary information and create a booking
     *  2. post to server
     *  3. update waitingTime
     *  4. update this collection to get new data
     * @param customerId the customer id
     * @param siteId     the site id
     * @return  the booking
     * @throws IOException
     * @throws InterruptedException
     */
    public String createNewEntity(String customerId, String siteId) throws IOException, InterruptedException {
        WebServicesTarget webService = new ServicesAdapter();
        SitesCollection sites = SitesCollection.getInstance();


        if (customerId != null && siteId != null) {
            String jsonNode = Booking.createNewEntity(customerId, siteId);
            ObjectNode response = webService.postData(Path.BOOKING.getPath(), jsonNode);
            Booking tempBooking = new Booking(response);
            sites.updateWaitingTime(siteId);

            update();

            return tempBooking.getPinCode();
        }
        return null;

    }







}

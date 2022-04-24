package bookings;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.node.ObjectNode;
import engine.DataSubscriber;
import enums.Path;
import enums.Query;
import testingSites.SitesCollection;
import users.User;
import users.UserCollection;
import webServiceAPI.ServicesAdapter;
import webServiceAPI.WebServicesTarget;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class BookingsCollection implements DataSubscriber {
    public static final String name = "Bookings Collection";
    private ArrayList<Booking> bookings = new ArrayList<>();
    private static BookingsCollection instance = null;

    private BookingsCollection() {
    }

    public static BookingsCollection getInstance() {
        if (instance == null) {
            instance = new BookingsCollection();
        }
        return instance;
    }

    public void addBooking(Booking booking) {
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

    /**
     * find the status from the PIN Code the user input
     *
     * @return String status
     * @paramn String pincode to find the related status
     */
    public Booking getStatusByPin(String pincode) {
        for (Booking booking : bookings) {
            String temp = booking.getPinCode();
            if (temp.equals(pincode)) {
                return booking;
            }
        }
        return null;
    }

    public boolean createNewBooking(String customerId, String siteId) throws IOException, InterruptedException {
        WebServicesTarget webService = new ServicesAdapter();
        SitesCollection sites = SitesCollection.getInstance();


        if (customerId != null && siteId != null) {
            Booking booking = new Booking(customerId, siteId);
            ObjectNode response = webService.postData(Path.BOOKING.getPath(), booking.toString());
            Booking tempBooking = new Booking(response);
            sites.updateWaitingTime(siteId);

            update();
            System.out.println("Please remember the PIN code" + tempBooking.getPinCode());
            return true;
        }
        return false;

    }







}

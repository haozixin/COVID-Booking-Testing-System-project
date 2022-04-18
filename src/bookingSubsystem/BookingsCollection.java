package bookingSubsystem;

import com.fasterxml.jackson.databind.node.ObjectNode;
import enums.Path;
import webServiceAPI.Services;
import webServiceAPI.WebServices;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class BookingsCollection {
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

    private void addBooking(Booking booking) {
        bookings.add(booking);
    }

    private void clearBookings() {
        bookings.clear();
    }

    public void update() throws IOException, InterruptedException {
        WebServices ws = new Services();
        ObjectNode[] objectNode = ws.getAllData(Path.BOOKING.getPath());
        clearBookings();

        for (ObjectNode node : objectNode) {
            Booking booking = new Booking(node);
            addBooking(booking);
        }
    }

    /**
     * find the status from the PIN Code the user input
     * @paramn String pincode to find the related status
     * @return String status
     */
    public String getStatusByPin(String pincode) {
        for (Booking booking : bookings) {
            String temp = booking.getPinCode();
            if (temp.equals(pincode)) {
                return booking.getStatus();
            }
        }
        return null;
    }
}

package bookings;

import com.fasterxml.jackson.databind.node.ObjectNode;
import engine.DataSubscriber;
import enums.Path;
import enums.Query;
import webServiceAPI.ServicesAdapter;
import webServiceAPI.WebServicesTarget;

import java.io.IOException;
import java.util.ArrayList;

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

    /**
     * find the status from the PIN Code the user input
     * @paramn String pincode to find the related status
     * @return String status
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
}

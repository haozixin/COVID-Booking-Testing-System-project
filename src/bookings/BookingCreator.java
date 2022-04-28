package bookings;

import java.io.IOException;

public abstract class BookingCreator {
    public abstract Booking createBooking(String customerId, String siteId) throws IOException, InterruptedException;
}

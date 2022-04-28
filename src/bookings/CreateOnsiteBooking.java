package bookings;

import com.fasterxml.jackson.databind.node.ObjectNode;
import enums.Path;
import testingSites.SitesCollection;
import webServiceAPI.ServicesAdapter;
import webServiceAPI.WebServicesTarget;

import java.io.IOException;

public class CreateOnsiteBooking extends BookingCreator {
    BookingsCollection bookings = BookingsCollection.getInstance();

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
    public Booking createBooking(String customerId, String siteId) throws IOException, InterruptedException {


        WebServicesTarget webService = new ServicesAdapter();
        SitesCollection sites = SitesCollection.getInstance();

        if (customerId != null && siteId != null) {
            String jsonNode = OnsiteBooking.createOnsiteBooking(customerId, siteId);
            ObjectNode response = webService.postData(Path.BOOKING.getPath(), jsonNode);
            Booking tempBooking = new OnsiteBooking(response);

            sites.updateWaitingTime(siteId);
            bookings.update();
            return tempBooking;
        }

        return null;
    }
}

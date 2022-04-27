package bookings;

import com.fasterxml.jackson.databind.node.ObjectNode;
import enums.Path;
import webServiceAPI.ServicesAdapter;
import webServiceAPI.WebServicesTarget;

import java.io.IOException;
import java.util.Scanner;

public class CreateHomeBooking extends BookingCreator{
    BookingsCollection bookings = BookingsCollection.getInstance();
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
    public Booking createBooking(String type, String customerId, String siteId) throws IOException, InterruptedException {
        boolean hasKit = false;

        System.out.println("Have you already has a RAT kit? (y/n): ");
        Scanner scanner = new Scanner(System.in);
        char result = scanner.next().charAt(0);
        if (result == 'y') {
            hasKit = true;
        }



        WebServicesTarget webService = new ServicesAdapter();
        if(type.equals(BookingsCollection.HOME_BOOKING_TYPE)){
            if (customerId != null && siteId != null) {
                String jsonNode = HomeBooking.createHomeBooking(customerId, siteId, hasKit);
                ObjectNode response = webService.postData(Path.BOOKING.getPath(), jsonNode);
                Booking tempBooking = new HomeBooking(response);
                // we assume that the HomeBooking won't consuming waiting time

                bookings.update();
                return tempBooking;
            }
        }
        return null;
    }
}

package actions;

import actors.Actor;
import bookings.Booking;
import bookings.BookingCreator;
import bookings.BookingsCollection;
import bookings.CreateHomeBooking;
import engine.DataCollection;
import engine.Entity;
import engine.actions.Action;
import testingSites.SitesCollection;
import utility.Utility;

import java.io.IOException;
import java.util.Scanner;

/**
 * Action to create a new booking(HomeBooking)
 */
public class HomeBookingAction extends Action{
    private DataCollection sites;
    private DataCollection bookings;
    private BookingCreator bookingCreator;

    /**
     * Constructor
     * Initialize the action
     */
    public HomeBookingAction() {
        name = "Home Booking";
        sites = SitesCollection.getInstance();
        bookings = BookingsCollection.getInstance();
        bookingCreator = new CreateHomeBooking();
    }

    @Override
    public String execute(Actor actor) throws IOException, InterruptedException {
        Utility.displayAction(name);
        sites.printWholeList();


        String customerId = actor.getIdFromToken();
        String siteId = findSite();


        Booking booking = bookingCreator.createBooking(customerId, siteId);

        sendURL(booking);
        displayQR(booking);


        return "Successfully booked a home testing site";
    }

    /**
     * Find the site id from the user input
     * only return the site that provide booking and testing services
     * @return site id that is provided to choose
     */
    private String findSite() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please input the Site's id that is selected by the customer : ");
        // we use nextLine(), because there might be space in the name
        String userInput = scanner.next();
        return userInput;
    }


    private void sendURL(Booking booking) {
        System.out.println("We have messaged the url to customer's phone --- " + booking.getURL());
    }

    private void displayQR(Booking booking) {
        System.out.println("Here is your QR code:  " + booking.getQRCode());
    }





    @Override
    public String menuDescription(Actor actor) {
        return "Go to book home testing";
    }


}

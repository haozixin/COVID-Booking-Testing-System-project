package actions;

import actors.Actor;
import bookings.Booking;
import bookings.BookingsCollection;
import engine.DataCollection;
import engine.Entity;
import engine.actions.Action;
import testingSites.SitesCollection;
import utility.Utility;

import java.io.IOException;
import java.util.Scanner;

public class HomeBookingAction extends Action{
    private DataCollection sites;
    private DataCollection bookings;

    public HomeBookingAction() {
        name = "Home Booking";
        sites = SitesCollection.getInstance();
        bookings = BookingsCollection.getInstance();
    }

    @Override
    public String execute(Actor actor) throws IOException, InterruptedException {
        Utility.displayAction(name);
        sites.printWholeList();


        String customerId = actor.getIdFromToken();
        String siteId = findSite();
        boolean hasKit = false;

        System.out.println("Have you already has a RAT kit? (y/n): ");
        Scanner scanner = new Scanner(System.in);
        char result = scanner.next().charAt(0);
        if (result == 'y') {
            hasKit = true;
        }

        Booking booking = bookings.createHomeBooking(BookingsCollection.HOME_BOOKING_TYPE, customerId, siteId, hasKit);

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
        // TODO: only return the site that provide booking and testing services
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please input the Site's id that is selected by the customer : ");
        // we use nextLine(), because there might be space in the name
        String userInput = scanner.next();
        return userInput;
    }


    private String sendURL(Booking booking) {
        return "We have messaged the url to customer's phone --- " + booking.getURL();
    }

    private String displayQR(Booking booking) {
        return "Here is your QR code:  " + booking.getQRCode();
    }





    @Override
    public String menuDescription(Actor actor) {
        return "Go to book home testing";
    }


}

package actions;

import actors.Actor;
import bookings.BookingsCollection;
import engine.DataCollection;
import engine.actions.Action;
import testingSites.SitesCollection;
import testingSites.TestingSite;
import users.UserCollection;
import utility.Utility;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class OnSiteBookingAction extends Action {
    private DataCollection sites;
    private DataCollection bookings;


    public OnSiteBookingAction() {
        name = "Booking Test Action";

        sites = SitesCollection.getInstance();
        bookings = BookingsCollection.getInstance();
    }

    @Override
    public String execute(Actor actor) throws IOException, InterruptedException {
        Utility.displayAction(name);

        // only show the sites that provide booking and testing services
        ArrayList<TestingSite> siteList;
        siteList = sites.filterByOnFactor(TestingSite.HAS_ON_SITE_BOOKING_FIELD,"true");
        for (TestingSite site : siteList) {
            site.display();
        }

//        sites.printWholeList();

        System.out.println("-----Provide customer's basic information that will be used to create a new booking-----");

        String customerId = findCustomer();
        String siteId = findSite();

        String pinCode = bookings.createOnsiteBooking(BookingsCollection.ONSITE_BOOKING_TYPE, customerId, siteId);
        if (pinCode != null) {
            messagePinCode(pinCode);
            return "Update(local and web side) data successfully";
        }
        return "Update(local and web side) data unsuccessfully";

    }

    private String findCustomer() {
        DataCollection users = UserCollection.getInstance();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please input the customer's userName : ");
        // we use nextLine(), because there might be space in the name
        String userInput = scanner.nextLine().trim();
        try {
            return users.findEntity(userInput).getEntityId();
        } catch (NullPointerException e) {
            return null;
        }

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

    /**
     * We use this method to simulate the system send a message with PIN code to the customer's phone
     * As a user/resident, the PIN that they received can be passed to the on-site facility staff to check the status of their booking
     * (assuming that the resident does not have direct access to the online system).
     */
    private void messagePinCode(String pinCode) {
        System.out.println("Customer has passed the PIN code to the on-site facility staff: " + pinCode);
    }


    @Override
    public String menuDescription(Actor actor) {
        return "Make the booking";
    }
}

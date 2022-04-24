package actions;

import actors.Actor;
import bookings.Booking;
import bookings.BookingsCollection;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.node.ObjectNode;
import engine.actions.Action;
import enums.Path;
import testingSites.SitesCollection;
import users.UserCollection;
import utility.Utility;
import webServiceAPI.ServicesAdapter;
import webServiceAPI.WebServicesTarget;

import java.io.IOException;
import java.util.Scanner;

public class BookingTestAction extends Action {
    private SitesCollection sites;
    private BookingsCollection bookings;


    public BookingTestAction() {
        name = "Booking Test Action";

        sites = SitesCollection.getInstance();
        bookings = BookingsCollection.getInstance();
    }

    @Override
    public String execute(Actor actor) throws IOException, InterruptedException {
        Utility.displayAction(name);


        sites.printList();

        System.out.println("-----Provide customer's basic information that will be used to create a new booking-----");
        String customerId = findCustomer();
        String siteId = findSite();

        boolean isSuccessful = bookings.createNewBooking(customerId, siteId);
        if (isSuccessful) {

            return "Update(local and web side) data successfully";
        }
        return "Update(local and web side) data unsuccessfully";

    }

    private String findCustomer() {
        UserCollection users = UserCollection.getInstance();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please input the customer's userName : ");
        // we use nextLine(), because there might be space in the name
        String userInput = scanner.nextLine().trim();
        try {
            return users.findUser(userInput).getUserId();
        } catch (NullPointerException e) {
            return null;
        }

    }

    private String findSite() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please input the Site's id that is selected by the customer : ");
        // we use nextLine(), because there might be space in the name
        String userInput = scanner.next();
        return userInput;
    }


    @Override
    public String menuDescription(Actor actor) {
        return "Make the booking";
    }
}

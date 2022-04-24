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
        boolean isSuccessful = bookings.createNewBooking();
        if (isSuccessful) {

            return "Update(local and web side) data successfully";
        }
        return "Update(local and web side) data unsuccessfully";


    }


    @Override
    public String menuDescription(Actor actor) {
        return "Make the booking";
    }
}

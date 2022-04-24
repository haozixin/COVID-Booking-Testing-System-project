package actions;

import actors.Actor;

import bookings.Booking;
import bookings.BookingsCollection;
import com.fasterxml.jackson.databind.node.ObjectNode;
import engine.actions.Action;
import testingSites.SitesCollection;
import utility.Utility;

import java.io.IOException;
import java.util.Scanner;


public class CheckBooking extends Action {
    public BookingsCollection bookingsCollection;

    public CheckBooking() {
        bookingsCollection = BookingsCollection.getInstance();
    }

    @Override
    public String execute(Actor actor) throws IOException, InterruptedException {
        Scanner s = new Scanner(System.in);
        String value;
        Booking b;

        System.out.print("Please input the PIN Code of the booking you want to check:");
        value = s.nextLine().trim();

        b = bookingsCollection.getStatusByPin(value);

        System.out.println(b.display());
        return "function running successfully";
    }

    @Override
    public String menuDescription(Actor actor) {
        return "Check the booking";
    }
}

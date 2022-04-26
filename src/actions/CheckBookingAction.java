package actions;

import actors.Actor;

import bookings.Booking;
import bookings.BookingsCollection;
import com.fasterxml.jackson.databind.node.ObjectNode;
import engine.DataCollection;
import engine.actions.Action;
import testingSites.SitesCollection;
import utility.Utility;

import java.io.IOException;
import java.util.Scanner;


public class CheckBookingAction extends Action {
    public DataCollection bookingsCollection;

    public CheckBookingAction() {
        bookingsCollection = BookingsCollection.getInstance();
    }

    @Override
    public String execute(Actor actor) throws IOException, InterruptedException {
        Scanner s = new Scanner(System.in);
        String value;
        Booking b;

        System.out.print("Please input the PIN Code of the booking you want to check:");
        value = s.nextLine().trim();

        try {
            b = (Booking) bookingsCollection.getStatusByPin(value);
            b.display();
            return "function running successfully";
        } catch (NullPointerException e) {
            return "No booking with this PIN Code";
        }




    }

    @Override
    public String menuDescription(Actor actor) {
        return "Check the booking";
    }
}

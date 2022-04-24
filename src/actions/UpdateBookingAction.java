package actions;

import actors.Actor;
import bookings.Booking;
import bookings.BookingsCollection;
import engine.actions.Action;

import java.io.IOException;
import java.util.Scanner;

public class UpdateBookingAction extends Action {

    @Override
    public String execute(Actor actor) throws IOException, InterruptedException {
       return null;
    }

    @Override
    public String menuDescription(Actor actor) {
        return "Update the booking";
    }
}

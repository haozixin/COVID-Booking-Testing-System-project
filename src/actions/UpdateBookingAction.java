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
        System.out.println("Do you have the RAT Kit?(y/n)");
        Scanner s = new Scanner(System.in);
        String answer = s.next();

        if (answer.equals("y")) {

        }
        return null;
    }

    @Override
    public String menuDescription(Actor actor) {
        return "Update the booking info";
    }
}

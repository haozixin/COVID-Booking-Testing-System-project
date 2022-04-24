package actions;

import actors.Actor;
import bookings.Booking;
import bookings.BookingsCollection;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.node.ObjectNode;
import engine.actions.Action;
import enums.Path;
import webServiceAPI.ServicesAdapter;
import webServiceAPI.WebServicesTarget;

import java.io.IOException;

public class BookingTestAction extends Action {
    private WebServicesTarget webService;
    public Booking booking;

    public BookingTestAction() {
        webService = new ServicesAdapter();
    }

    @Override
    public String execute(Actor actor) throws IOException, InterruptedException {
        booking = new Booking();
        webService.postData(Path.BOOKING.getPath(), booking.toString());
        return "Post data successfully";
    }

    @Override
    public String menuDescription(Actor actor) {
        return "Make the booking";
    }
}

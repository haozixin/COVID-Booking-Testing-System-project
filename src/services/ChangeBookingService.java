package services;

import controllers.ChangeBookingController;
import engine.CurrentOperator;
import engine.Service;
import models.bookings.BookingModel;
import models.bookings.OnsiteBookingModel;
import views.ChangeBookingView;

/**
 * Action to change the booking.
 */
public class ChangeBookingService extends Service{


    @Override
    public String execute(CurrentOperator currentOperator) {
        BookingModel booking = new OnsiteBookingModel();
        ChangeBookingView changeBookingView = new ChangeBookingView(booking);
        controller = new ChangeBookingController(changeBookingView, booking);
        changeBookingView.setVisible(true);
        return "";
    }

    @Override
    public String menuDescription(CurrentOperator currentOperator) {
        return "Change my booking(venue and timing)";
    }
}

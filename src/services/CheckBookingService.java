package services;

import controllers.CheckBookingController;
import engine.Service;
import engine.CurrentOperator;
import models.BookingModel;
import models.CollectionModel;
import models.OnsiteBookingModel;
import views.CheckBookingByPINView;

/**
 * Action to check the booking.
 */
public class CheckBookingService extends Service {
    @Override
    public String execute(CurrentOperator currentOperator) {
        BookingModel booking = new OnsiteBookingModel();
        CheckBookingByPINView view = new CheckBookingByPINView(booking);
        controller = new CheckBookingController(view, booking);
        view.setVisible(true);
        return "";
    }

    @Override
    public String menuDescription(CurrentOperator currentOperator) {
        return "Help residents change their booking";
    }
}
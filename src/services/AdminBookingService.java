package services;

import controllers.AdminBookingController;
import engine.CurrentOperator;
import engine.Service;
import engine.Services;
import engine.adminNotification.BookingPublisher;
import engine.adminNotification.Publisher;
import models.bookings.BookingModel;
import models.bookings.OnsiteBookingModel;
import views.AdminBookingView;

import java.util.List;

/**
 * Action about admin booking
 */
public class AdminBookingService extends Service{
    @Override
    public String execute(CurrentOperator currentOperator) {
        // Broadcast new message to all subscribers (within a range - for example, only subscribers within the same facility)
//        Publisher publisher = BookingPublisher.getInstance();
//        currentOperator.broadCast(publisher);



        BookingModel bookingModel = new OnsiteBookingModel();
        AdminBookingView adminBookingView = new AdminBookingView(bookingModel);
        controller = new AdminBookingController(adminBookingView, bookingModel);
        adminBookingView.updateView();
        adminBookingView.setVisible(true);
        return "";
    }

    @Override
    public String menuDescription(CurrentOperator currentOperator) {
        return "Admin Booking Interface";
    }
}

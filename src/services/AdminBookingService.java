package services;

import controllers.AdminBookingController;
import engine.CurrentOperator;
import engine.Service;
import engine.Services;
import engine.adminNotification.BookingPublisher;
import engine.adminNotification.Publisher;
import models.bookings.BookingModel;
import views.AdminBookingView;

import java.util.List;

public class AdminBookingService extends Service{
    @Override
    public String execute(CurrentOperator currentOperator) {
        // Broadcast new message to all subscribers (within a range - for example, only subscribers within the same facility)
//        Publisher publisher = BookingPublisher.getInstance();
//        currentOperator.broadCast(publisher);

        // retrieve the message published by others
//        String message = currentOperator.receiveMessage();
//        System.out.println(message);


        AdminBookingView adminBookingView = new AdminBookingView();
//        controller = new AdminBookingController(adminBookingView);
        adminBookingView.setVisible(true);
        return "";
    }

    @Override
    public String menuDescription(CurrentOperator currentOperator) {
        return "Admin Booking Interface";
    }
}

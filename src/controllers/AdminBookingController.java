package controllers;

import engine.CurrentOperator;
import engine.Service;
import engine.adminNotification.BookingPublisher;
import engine.adminNotification.Publisher;
import models.bookings.BookingModel;
import services.ChangeBookingService;
import services.OnSiteBookingService;
import views.AdminBookingView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminBookingController extends Controller {
    private AdminBookingView view;
    private BookingModel model;

    public AdminBookingController(AdminBookingView view, BookingModel model) {
        this.model = model;
        this.view = view;
        view.addNewBookingListener(new NewBookingListener());
        view.addDeleteBookingListener(new DeleteBookingListener());
        view.addModifiedBookingListener(new ModifiedBookingListener());
    }

    class NewBookingListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            Service service = new OnSiteBookingService();
            service.execute(CurrentOperator.getInstance());
        }
    }

    class DeleteBookingListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String id = view.getBookingId();
            if (id.equals("")) {
                JOptionPane.showMessageDialog(view, "Please enter a booking id");
            }else{


                boolean result = model.deleteBooking(id);
                if (result) {
                    view.update();

                    String facilityId = model.getVenueId();
                    // Broadcast new message to all subscribers (within a range - for example, only subscribers within the same facility)
                    Publisher publisher = BookingPublisher.getInstance();
                    CurrentOperator.getInstance().broadCast(publisher, "a booking was deleted.", facilityId);
                }
            }
        }
    }

    class ModifiedBookingListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            Service service = new ChangeBookingService();
            service.execute(CurrentOperator.getInstance());
        }
    }
}

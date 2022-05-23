package controllers;

import engine.CurrentOperator;
import engine.Service;
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

package controllers;

import models.bookings.BookingModel;
import views.AdminBookingView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminBookingController {
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

        }
    }

    class DeleteBookingListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String id = view.getBookingId();
            if (id.equals("")) {
                JOptionPane.showMessageDialog(view, "Please enter a booking id");
            }else{
                model.deleteBooking(id);
            }
        }
    }

    class ModifiedBookingListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }
}

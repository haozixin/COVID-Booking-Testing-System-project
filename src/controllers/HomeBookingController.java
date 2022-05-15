package controllers;

import com.fasterxml.jackson.databind.node.ObjectNode;
import enums.Path;
import models.Actor;
import models.Booking;
import models.HomeBooking;
import views.HomeBookingView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class HomeBookingController extends Controller {
    private HomeBookingView view;
    private HomeBooking bookingModel;
    public HomeBookingController(HomeBookingView view, HomeBooking bookingModel) {
        this.view = view;
        this.bookingModel = bookingModel;
        view.addButtonListener(new ButtonListener());
    }

    /**
     *  Create a home-booking for a user
     *  1. fill all necessary information and create a booking
     *  2. post to server
     */
    class ButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String customerId = Actor.getInstance().getIdFromToken();
            String siteId = view.getSiteId();

            if (!siteId.equals("")) {
                boolean hasKit = false;
                if (view.getSelectedItem().equals(HomeBookingView.HAS_RAT_KIT)) {
                    hasKit = true;
                }
                bookingModel.setSchema(customerId, siteId, hasKit);
                try {
                    bookingModel.postModelToServer(Path.BOOKING.getPath());
                } catch (IOException | InterruptedException ex) {
                    ex.printStackTrace();
                }

                view.update();
            }else{
                JOptionPane.showMessageDialog(view, "Please select a testing site and enter its Id");
            }


        }
    }
}

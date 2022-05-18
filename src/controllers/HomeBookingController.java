package controllers;

import enums.Path;
import engine.CurrentOperator;
import models.HomeBookingModel;
import views.HomeBookingView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class HomeBookingController extends Controller {
    private HomeBookingView view;
    private HomeBookingModel bookingModel;
    public HomeBookingController(HomeBookingView view, HomeBookingModel bookingModel) {
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
            String customerId = CurrentOperator.getInstance().getIdFromToken();
            String siteId = view.getSiteId();
            String date = view.getDate();
            String time = view.getTime();

            if (!siteId.equals("") && !date.equals("") && !time.equals("")) {
                boolean hasKit = false;
                if (view.getSelectedItem().equals(HomeBookingView.HAS_RAT_KIT)) {
                    hasKit = true;
                }
                String startTime = date + " " + time;
                bookingModel.setSchema(customerId, siteId, hasKit, startTime);
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

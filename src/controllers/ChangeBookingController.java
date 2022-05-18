package controllers;

import models.OnsiteBookingModel;
import views.ChangeBookingView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChangeBookingController extends Controller {
    private ChangeBookingView view;
    private OnsiteBookingModel model;

    public ChangeBookingController(ChangeBookingView view, OnsiteBookingModel model) {
        this.view = view;
        this.model = model;
        view.addButtonListener(new ButtonListener());
    }

    class ButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String siteId = view.getSiteId();
            String bookingId = view.getBookingId();
            String date = view.getDate();
            String time = view.getTime();
            String startTime = "";
            // get onsite booking

            if (bookingId.equals("")) {
                JOptionPane.showMessageDialog(view, "Please enter a booking ID first");
            } else {

                if (model.canBeChanged(bookingId)) {
                    if (!date.equals("YYYY-MM-DD") && !time.equals("HH:MM")) {
                        // if one of the fields is not empty, change the booking
                        startTime = date + " " + time;
                    }
                    if (!siteId.equals("") || !startTime.equals("")) {
                        model.changeBooking(siteId, startTime);

                        view.update();
                    } else {
                        JOptionPane.showMessageDialog(view, "Please change at least one (venue or start dateTime)");
                    }

                } else {
                    JOptionPane.showMessageDialog(view, "This booking cannot be changed(possible reasons: 'be canceled'/has expired/has been checked out)");
                }
            }
        }
    }

}

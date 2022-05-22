package controllers;

import mementos.BookingCaretaker;
import mementos.Caretaker;
import models.bookings.BookingModel;
import views.ChangeBookingView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ChangeBookingController extends Controller {
    private ChangeBookingView view;
    private BookingModel model;

    public ChangeBookingController(ChangeBookingView view, BookingModel model) {
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
                    // save the change into Memento
                    Caretaker bookingCareTaker = BookingCaretaker.getInstance();
                    bookingCareTaker.addMemento(model.save());

                    if (!date.equals("YYYY-MM-DD") && !time.equals("HH:MM")) {
                        // if one of the fields is not empty, change the booking
                        startTime = date + " " + time;
                    }
                    if (!siteId.equals("") || !startTime.equals("")) {
                        Date validTime = null;
                        try {
                            validTime = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(startTime);
                            if (validTime.before(new Date())) {
                                JOptionPane.showMessageDialog(view, "Please enter a valid time");
                            }
                            else{
                                // change the booking's venue and start time locally and remotely
                                model.changeBooking(siteId, startTime);

                                view.update();
                            }
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }

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

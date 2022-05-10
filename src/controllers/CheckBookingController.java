package controllers;

import com.fasterxml.jackson.databind.node.ObjectNode;
import enums.Path;
import models.Booking;
import models.Collection;
import views.CheckBookingView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CheckBookingController extends Controller {
    private CheckBookingView view;
    private Collection dataModel;

    public CheckBookingController(CheckBookingView view, Collection dataModel) {
        this.view = view;
        this.dataModel = dataModel;
        this.view.addButtonListener(new ButtonListener());
    }

    class ButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String pinCode = view.getPinCode();
            // if input of pin code is not empty
            // do the rest logic
            if (!pinCode.equals("")) {
                dataModel.updateCollection(Path.BOOKING.getPath());
                ArrayList<ObjectNode> bookings = dataModel.filterByOnFactor(Booking.SMS_PIN_FIELD, pinCode);

                if (bookings.size() > 0) {
                    dataModel.setCollection(bookings);
                    view.update();
                }
                else{
                    JOptionPane.showMessageDialog(null, "No booking found with this pin code");
                }

            }
            else{
                JOptionPane.showMessageDialog(null, "Please enter the pin code");
            }

        }
    }
}
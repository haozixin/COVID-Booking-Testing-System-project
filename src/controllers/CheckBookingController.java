package controllers;

import com.fasterxml.jackson.databind.node.ObjectNode;
import engine.CurrentOperator;
import engine.Service;
import enums.Path;
import models.BookingModel;
import models.CollectionModel;
import services.ChangeBookingService;
import views.CheckBookingByPINView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CheckBookingController extends Controller {
    private CheckBookingByPINView view;
    private BookingModel bookingModel;


    public CheckBookingController(CheckBookingByPINView view, BookingModel bookingModel) {
        this.view = view;
        this.bookingModel = bookingModel;
        this.view.addButtonListener(new ButtonListener());
    }

    class ButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String pinCode = view.getPinCode();
            // if input of pin code is not empty
            // do the rest logic
            String bookingId = view.getBookingId();
            if (!pinCode.isEmpty() && !bookingId.isEmpty()) {
                // verify if the pin code is correct
                boolean result = bookingModel.verifyBooking( bookingId, pinCode);
                if(result){
                    // if the pin code is correct
                    view.update();
                    JOptionPane.showMessageDialog(view, "Verification Successful");
                    Service service = new ChangeBookingService();
                    service.execute(CurrentOperator.getInstance());
                }
                else{
                    // if the pin code is not correct
                    JOptionPane.showMessageDialog(view, "Invalid PIN Code or Booking ID");
                }
            }
        }
    }
}
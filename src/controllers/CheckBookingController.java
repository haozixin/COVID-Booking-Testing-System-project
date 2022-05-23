package controllers;

import engine.CurrentOperator;
import engine.Service;
import models.bookings.BookingModel;
import services.ChangeBookingService;
import views.CheckBookingByPINView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Controller for the CheckBookingByPINView.
 */
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
                    JOptionPane.showMessageDialog(view, "Correct PIN code and Booking ID");
                    Service service = new ChangeBookingService();
                    service.execute(CurrentOperator.getInstance());
                }
                else{
                    // if the pin code is not correct
                    JOptionPane.showMessageDialog(view, "Invalid PIN Code or Booking ID");
                }
            }
            else if(!pinCode.isEmpty() || !bookingId.isEmpty()){
                // if the pin code is empty, then we use the booking id
                if (bookingModel.getBookingById(bookingId)){

                    view.update();
                    JOptionPane.showMessageDialog(view, "Correct Booking ID");
                    Service service = new ChangeBookingService();
                    service.execute(CurrentOperator.getInstance());
                }
                // if the booking id is empty, then we use the pin code
                else if (bookingModel.getBookingByPin(pinCode)){

                    view.update();
                    JOptionPane.showMessageDialog(view, "Correct PIN code");
                    Service service = new ChangeBookingService();
                    service.execute(CurrentOperator.getInstance());
                }
                else{
                    JOptionPane.showMessageDialog(view, "Wrong PIN code or Booking ID");
                }
            }
            else{
                JOptionPane.showMessageDialog(view, "Please enter PIN code or Booking ID at least one");
            }
        }
    }
}
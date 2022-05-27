package controllers;

import models.bookings.BookingModel;
import models.bookings.HomeBookingModel;
import views.ScanQRView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Controller for the ScanQRView
 */
public class ScanQRController extends Controller {
    private BookingModel bookingModel;
    private ScanQRView view;

    public ScanQRController(HomeBookingModel homeBookingModel, ScanQRView view) {
        this.bookingModel = homeBookingModel;
        this.view = view;
        view.addButtonListener(new ButtonListener());
    }

    class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String qrCode = view.getQRCode();
            if(!qrCode.equals("")) {
               bookingModel.getBookingByQR(qrCode);
               boolean isUpdated = bookingModel.updateRATKitInfo(true);
               if(isUpdated) {
                   bookingModel.setIsUpdated(true);
               }
                view.update();
            }
            else{
                JOptionPane.showMessageDialog(null, "Please fill in all fields");
            }
        }
    }
}

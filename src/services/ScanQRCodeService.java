package services;

import controllers.ScanQRController;
import engine.Service;
import engine.CurrentOperator;
import models.HomeBookingModel;
import views.ScanQRView;

/**
 * Scan QR Code Action
 */
public class ScanQRCodeService extends Service {

    @Override
    public String execute(CurrentOperator currentOperator) {
        // we assume that scanning QR code is a process/operation to get all necessary information and set "hasRATKit" to true
        // As we use UUID to emulate the QR code, so scanning QR code is same as the process/operation of inputting UUID to the system

        HomeBookingModel bookingModel = new HomeBookingModel();
        ScanQRView scanQRView = new ScanQRView(bookingModel);
        controller = new ScanQRController(bookingModel,scanQRView);
        scanQRView.setVisible(true);

        return "";
    }

    @Override
    public String menuDescription(CurrentOperator currentOperator) {
        return "Scan QR Code";
    }
}

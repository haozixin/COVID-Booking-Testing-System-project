package controllers;

import enums.Path;
import models.OnsiteBookingModel;
import models.CovidTestingSiteModel;
import models.UserModel;
import views.OnSiteBookingView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class OnSiteBookingController extends Controller {
    private OnsiteBookingModel onsiteBookingModel;
    private CovidTestingSiteModel covidTestingSiteModel;
    private UserModel userModel;
    private OnSiteBookingView view;

    public OnSiteBookingController(OnsiteBookingModel onsiteBookingModel, UserModel userModel, CovidTestingSiteModel covidTestingSiteModel, OnSiteBookingView view) {
        this.covidTestingSiteModel = covidTestingSiteModel;
        this.userModel = userModel;
        this.onsiteBookingModel = onsiteBookingModel;
        this.view = view;
        this.view.addButtonListener(new ButtonListener());
    }

    /**
     * Create an Onsite-booking for a user
     * 1. fill all necessary information(get from view) on the created bookingModel
     * 2. post to server
     * 3. update waitingTime
     */
    class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String siteId = view.getSiteId();
            String userName = view.getUserNameField();
            String date = view.getDateField();
            String time = view.getTimeField();
            if (siteId.equals("") || userName.equals("") || date.equals("") || time.equals("")) {
                JOptionPane.showMessageDialog(null, "Please fill in all fields");
            } else {
                boolean isFound = findPatientInfo(userName);
                if (isFound) {
                    // if value is not empty, do the rest logic

                    String patientId = userModel.getId();
                    String startTime = date + " " + time;
                    onsiteBookingModel.setSchema(patientId, siteId, startTime);
                    try {
                        // post model/data to server
                        onsiteBookingModel.postModelToServer(Path.BOOKING.getPath());
                        // update waitingTime
                        covidTestingSiteModel.updateWaitingTime(siteId);

                        // simulate the sending pin code to customer's phone here
                        String phoneNumber = userModel.getPhoneNumber();
                        String pin = onsiteBookingModel.getPinCode();
                        if (phoneNumber != null && pin != null) {
                            JOptionPane.showMessageDialog(view, "We have messaged the pin code to customer! \n" + "PinCode: " + pin + "\n" + "PhoneNumber: " + phoneNumber);
                        }



                    } catch (IOException | InterruptedException ex) {
                        ex.printStackTrace();
                    }

                    // the view only need to update when doing something
                    view.update();

                } else {
                    JOptionPane.showMessageDialog(view, "We cannot find the patient's information in our system");
                }
            }

        }
    }

    private boolean findPatientInfo(String userName) {
        boolean isFound = false;
        try {
            isFound = userModel.findBookingsByUserName(userName);
        } catch (IOException | InterruptedException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(view, userModel.getResponseMessage());
        }
        return isFound;
    }

}

package controllers;

import enums.Path;
import models.Actor;
import models.CovidTest;
import models.User;
import views.InterviewView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class InterviewController extends Controller {
    private InterviewView interviewView;
    private User userModel;
    private CovidTest covidTestModel;



    public InterviewController(InterviewView view, User userModel, CovidTest covidTestModel) {
        if (view != null && userModel != null && covidTestModel != null) {

            interviewView = view;
            this.userModel = userModel;
            this.covidTestModel = covidTestModel;
            interviewView.addButtonListener(new InterviewListener());
            interviewView.addButton2Listener(new InterviewListener2());
            interviewView.addButton3Listener(new InterviewListener3());
        }else{
            throw new IllegalArgumentException("Invalid arguments");
        }


    }


    /**
     * This class is the listener for the buttons in the interview view
     * Get a suggestion from the system based on the user's answers
     */
    class InterviewListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int counter = interviewView.countCheckBox();
            String suggestion = "";
            if(counter >=3){
                suggestion = "According to the symptoms, our testing type suggestion is: PCR";
            }else{
                suggestion = "According to the symptoms, our testing type suggestion is: RAT";
            }
            JOptionPane.showMessageDialog(interviewView,suggestion);
        }

    }

    /**
     * This class is the listener for the buttons in the interview view
     * Find user by userName (instead of user id because nobody will remember their long-id)
     */
    class InterviewListener2 implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {

            findPatientInfo();
        }
    }

    private void findPatientInfo() {
        String userName = interviewView.getUserNameTextField();
        if (!userName.equals("")){
            try {
                boolean isFound = userModel.findBookingsByUserName(userName);
                if (isFound){
                    interviewView.update();
                }
                else{
                    JOptionPane.showMessageDialog(interviewView,"User not found - please sign up an account first");
                }
            } catch (IOException | InterruptedException ex) {
                ex.printStackTrace();
            }
        }
        else{
            JOptionPane.showMessageDialog(interviewView,"Please enter a valid userName");
        }
    }

    class InterviewListener3 implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            String finalSuggestion = interviewView.getCmb();
            String userName = interviewView.getUserNameTextField();

            String healthcareWorkerId = Actor.getInstance().getIdFromToken();
            String bookingId = interviewView.getBookingId();

            if (!finalSuggestion.equals(InterviewView.EMPTY_OPTION) && !userName.equals("") && !bookingId.equals("") && !healthcareWorkerId.equals("")){
                String patientId = userModel.getId();
                // if the patient is not in the system. It means we haven't got data from database
                // (actor don't click the first button to get booking's information and directly click the second button to create a new covid test)
                if(patientId == null){
                    findPatientInfo();
                }
                // if we have already get the patient id.
                if (patientId != null){
                    covidTestModel.setSchema(finalSuggestion, patientId, healthcareWorkerId, bookingId);
                    try {
                        covidTestModel.postModelToServer(Path.TEST.getPath());
                    } catch (IOException | InterruptedException ex) {
                        ex.printStackTrace();
                    }

                }
                interviewView.update();
            }
            else{
                JOptionPane.showMessageDialog(interviewView,"Please fill in and select all the fields");
            }
        }
    }

}

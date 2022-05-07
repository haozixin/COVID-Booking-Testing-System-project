package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import models.Actor;
import models.Collection;
import models.Model;
import models.User;
import views.InterviewView;
import views.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class InterviewController extends Controller {
    private InterviewView interviewView;
    private User dataModel;



    public InterviewController(View view, Model dataModel) {
        if (view instanceof InterviewView && dataModel instanceof User) {
            interviewView = (InterviewView) view;
            this.dataModel = (User) dataModel;
            interviewView.addButtonListener(new InterviewListener());
            interviewView.addButton2Listener(new InterviewListener2());
        }else{
            throw new IllegalArgumentException("Invalid arguments");
        }


    }


    class InterviewListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            getSuggestion();
            String finalSuggestion = interviewView.getCmb();
            String userName = interviewView.getUserNameTextField();
            String healthcareWorkerId = Actor.getInstance().getIdFromToken();
            String bookingId = interviewView.getBookingId();



        }

        private void getSuggestion() {
            Component[] components = interviewView.getComponents();
            int counter = 0;
            String suggestion = "";
            for (Component component : components) {
                if (component instanceof JCheckBox) {
                    JCheckBox jcb = (JCheckBox) component;
                    if (jcb.isSelected()) {
                        counter++;
                    }
                }
            }
            if(counter >=3){
                suggestion = "According to the symptoms, our testing type suggestion is: PCR";
            }else{
                suggestion = "According to the symptoms, our testing type suggestion is: RAT";
            }
            JOptionPane.showMessageDialog(interviewView,suggestion);
        }
    }

    class InterviewListener2 implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {

            if (!interviewView.getUserNameTextField().equals("")){
                String userName = interviewView.getUserNameTextField();
                try {
                    dataModel.findBookingsByUserName(userName);
                    interviewView.update();
                } catch (IOException | InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
            else{
                JOptionPane.showMessageDialog(interviewView,"Please enter a valid username");
            }
        }
    }

}

package actions;

import actors.Actor;
import bookings.Booking;
import engine.actions.Action;
import testingSites.Location;
import testingSites.TestingSite;
import testings.CovidTest;
import users.User;
import users.UserCollection;
import utility.Utility;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * the action of an interview is used for healthcare workers/administerers
 * About On-site Testing Subsystem
 *
 */
public class InterviewAction extends Action {
    private ArrayList<String> symptomFields;
    private UserCollection users;


    public InterviewAction() {
        name = "Interview Action";

        symptomFields = new ArrayList<>();
        symptomFields.add("flu");
        symptomFields.add("cough");
        symptomFields.add("fever");
        symptomFields.add("headache");
        symptomFields.add("chest pain");
        symptomFields.add("diarrhoea");

        users = UserCollection.getInstance();

    }


    @Override
    public String execute(Actor actor) throws IOException, InterruptedException {


        String suggestion = conductInterview();

        // Step1
        // Find the existing booking through the user name. If there is no booking, make a reservation first
        Scanner s = new Scanner(System.in);
        System.out.print("Please input the customer's userName to get their booking: ");
        String userName = s.next();
        String customerId = users.findUser(userName).getUserId();
        Utility.displayJsonList(Booking.className, users.searchForBooking(userName));
        String actorId = actor.getIdFromToken();



        // Step2 create COVID test
        System.out.print("Chose the bookingId to create a COVID test: ");
        String bookingId = s.next();
        CovidTest covidTest = new CovidTest(suggestion, customerId, actorId, bookingId);
        covidTest.postTestingData();


        return "We have recorded your decision and created a COVID test for you";
    }

    private String conductInterview(){
        // interview process flow
        String answer;
        int no_of_yes = 0;

        Utility.displayAction(name);

        for (String symptom : symptomFields) {
            System.out.print("Does the customer have a " +symptom+" ? (y/n): ");
            Scanner s = new Scanner(System.in);
            answer = s.next();
            if (answer.equals("y")) {
                no_of_yes += 1;
            }
        }

        if (no_of_yes >= 3) {
            System.out.println("According to the symptoms, our testing type suggestion is: PCR");
        }
        else {
            System.out.println("According to the symptoms, our testing type suggestion is: RAT");
        }

        System.out.print("\nYour final testing type decision is(PCR/RAT): ");
        Scanner s = new Scanner(System.in);
        answer = s.next();

        while(!(answer.equals("RAT") || answer.equals("PCR"))){
            System.out.println("Error, please input the correct testing type");
            System.out.println("\nYour(healthcare workers) final testing type decision is(PCR/RAT/): ");
            answer = s.next();
        }
        return answer;
    }

    @Override
    public String menuDescription(Actor actor) {
        return "Interview";
    }
}

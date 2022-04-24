package actions;

import actors.Actor;
import engine.actions.Action;
import testingSites.Location;
import testingSites.TestingSite;
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

    public InterviewAction() {
        name = "Interview Action";

        symptomFields = new ArrayList<>();
        symptomFields.add("flu");
        symptomFields.add("cough");
        symptomFields.add("fever");
        symptomFields.add("headache");
        symptomFields.add("chest pain");
        symptomFields.add("diarrhoea");
    }


    @Override
    public String execute(Actor actor) throws IOException, InterruptedException {
        String answer;
        int no_of_yes = 0;

        Utility.displayAction(name);

        for (String symptom : symptomFields) {
            System.out.println("Does the customer have a " +symptom+" ? (y/n): ");
            Scanner s = new Scanner(System.in);
            answer = s.next();
            if (answer.equals("y")) {
                no_of_yes += 1;
            }
        }

        if (no_of_yes >= 3) {
            System.out.println("According to the symptoms, our testing type suggestion: PCR");
        }
        else {
            System.out.println("According to the symptoms, our testing type suggestion: RAT");
            }

        System.out.println("\nYour final testing type decision is(PCR/RAT): ");
        Scanner s = new Scanner(System.in);
        answer = s.next();

        while(!(answer.equals("RAT") || answer.equals("PCR"))){
            System.out.println("Error, please input the correct testing type");
            System.out.println("\nYour final testing type decision is(PCR/RAT): ");
            answer = s.next();
        }
        return "Confirmed the testing type";
    }

    @Override
    public String menuDescription(Actor actor) {
        return "Interview";
    }
}

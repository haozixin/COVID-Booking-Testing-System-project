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
            answer = s.nextLine().trim();
            if (answer =="y") {
                no_of_yes += 1;
            }
        }


//        System.out.println("Does the customer have a flu? (y/n): ");
//        answer = s.nextLine().trim();
//        if (answer == "y") {
//            no_of_yes += 1;
//        }
//
//        //TODO: 做一个interview的流程， 问几个问题， 根据答案来推荐做哪种测试， 然后再问医师的决定，记录并上传”决定“
//        System.out.println("Does the customer have a cough? (y/n): ");
//        answer = s.nextLine().trim();
//        if (answer == "y") {
//            no_of_yes += 1;
//        }
//
//        System.out.println("Does the customer have a fever? (y/n): ");
//        answer = s.nextLine().trim();
//        if (answer == "y") {
//            no_of_yes += 1;
//        }
//
//        System.out.println("Does the customer have a headache? (y/n): ");
//        answer = s.nextLine().trim();
//        if (answer == "y") {
//            no_of_yes += 1;
//        }
//
//        System.out.println("Does the customer have a chest pain? (y/n): ");
//        answer = s.nextLine().trim();
//        if (answer == "y") {
//            no_of_yes += 1;
//        }
//
//        System.out.println("Does the customer have a diarrhoea? (y/n): ");
//        answer = s.nextLine().trim();
//        if (answer == "y") {
//            no_of_yes += 1;
//        }
//
        return "success";
    }

    @Override
    public String menuDescription(Actor actor) {
        return "Interview";
    }
}

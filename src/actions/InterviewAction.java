package actions;

import actors.Actor;
import engine.actions.Action;
import utility.Utility;

import java.io.IOException;
import java.util.Scanner;

/**
 * the action of an interview is used for healthcare workers/administerers
 * About On-site Testing Subsystem
 *
 */
public class InterviewAction extends Action {

    public InterviewAction() {
        name = "Interview Action";
    }

    @Override
    public String execute(Actor actor) throws IOException, InterruptedException {
        Utility.displayAction(name);
        Scanner scanner = new Scanner(System.in);

        System.out.println("Does the customer have a flu? (y/n): ");
        //TODO: 做一个interview的流程， 问几个问题， 根据答案来推荐做哪种测试， 然后再问医师的决定，记录并上传”决定“

        return null;
    }

    @Override
    public String menuDescription(Actor actor) {
        return null;
    }
}

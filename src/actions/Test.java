package actions;

import actors.Actor;
import engine.actions.Action;

import java.io.IOException;

public class Test extends Action {
    @Override
    public String execute(Actor actor) throws IOException, InterruptedException {
        System.out.println("function testing is running");
        return "function running successfully";
    }

    @Override
    public String menuDescription(Actor actor) {
        return "LoginAction";
    }

    @Override
    public String hotkey() {
        return "z";
    }
}

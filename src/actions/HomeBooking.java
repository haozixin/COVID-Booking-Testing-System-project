package actions;

import actors.Actor;
import engine.actions.Action;
import utility.Utility;

import java.io.IOException;

public class HomeBooking extends Action{

    @Override
    public String execute(Actor actor) throws IOException, InterruptedException {
        Utility.displayAction(name);

        System.out.println("Remember write \"Home booking\" into the note field.");
        return null;
    }

    @Override
    public String menuDescription(Actor actor) {
        return null;
    }
}

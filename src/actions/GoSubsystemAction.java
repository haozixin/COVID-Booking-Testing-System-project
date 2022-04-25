package actions;

import actors.Actor;
import engine.actions.Action;
import subSystems.CovidBAndTSystem;

import java.io.IOException;

public class GoSubsystemAction extends Action {
    private CovidBAndTSystem covidBAndTSystem;

    public GoSubsystemAction(CovidBAndTSystem covidBAndTSystem) {
        this.covidBAndTSystem = covidBAndTSystem;
    }
    @Override
    public String execute(Actor actor) throws IOException, InterruptedException {
        // because the attribute of wantsGoBack is set true when the user click go back button,
        // we have to reset it to false when the user chose this action before the subsystem runs, otherwise, the user cannot go into the subsystem.
        actor.setWantsGoBack(false);

        covidBAndTSystem.addUser(actor);
        covidBAndTSystem.run();
        return "You are in the main menu";
    }

    @Override
    public String menuDescription(Actor actor) {
        return "Go to "+covidBAndTSystem.getSystemName();
    }
}

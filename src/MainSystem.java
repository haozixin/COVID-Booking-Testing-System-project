import actions.GoSubsystemAction;
import actors.Actor;
import engine.Display;
import engine.Menu;
import engine.actions.Action;
import engine.actions.Actions;
import subSystems.CovidBAndTSystem;
import utility.Utility;

import java.io.IOException;
import java.util.ArrayList;

/**
 * MainSystem class
 * Facade class for the whole system
 * it only needs to consider if the actor is logged in or not, more judgement will be done in each subsystem
 */
public class MainSystem {
    private static final String systemName = "--COVID Booking & Testing System--";
    private ArrayList<CovidBAndTSystem> systems;
    private ArrayList<String> dashBoard;
    private Actor actor;
    private Menu mainMenu;
    private Actions actions;
    private Display display;


    /**
     * Constructor of mainSystem
     */
    public MainSystem() {
        dashBoard = Utility.setDashboard(100, 5, systemName);
        mainMenu = new Menu();
        actions = new Actions();
        systems = new ArrayList<>();
        display = new Display();
    }

    /**
     *  add a sub-system to the system list
     * @param system sub-system
     */
    public void addSubSystem(CovidBAndTSystem system) {
        systems.add(system);
    }

    /**
     * set the actor(like agent for real actor) for the system
     * @param actor actor
     */
    public void addActor(Actor actor) {
        this.actor = actor;
    }

    /**
     * show the dashboard of the system
     * help the user to know where is he/she
     */
    private void showDashBoard() {
        Utility.printArrayList(dashBoard);
    }

    /**
     * the engine of the system
     */
    public void run() {

        if (actor == null)
            throw new IllegalStateException();

        while (true) {
            showDashBoard();
            actions.clear();
            for (CovidBAndTSystem system : systems) {
                actions.add(new GoSubsystemAction(system));
            }


            Action action = mainMenu.showMenu(actor, actions, display);
            String result = null;
            try {
                result = action.execute(actor);
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
            display.println(result);
        }
    }


}
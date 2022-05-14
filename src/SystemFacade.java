import engine.Service;
import engine.Services;
import engine.Menu;
import models.Actor;
import services.GoSubsystemService;
import subSystems.CovidBAndTSystem;
import utility.Utility;

import java.util.ArrayList;

/**
 * MainSystem class
 * Facade class for the whole system
 * it only needs to consider if the actor is logged in or not, more judgement will be done in each subsystem
 */
public class SystemFacade {
    private static final String systemName = "--COVID Booking & Testing System--";
    private ArrayList<CovidBAndTSystem> systems;
    private ArrayList<String> dashBoard;
    private Actor actor;
    private Menu mainMenu;
    private Services services;


    /**
     * Constructor of mainSystem
     */
    public SystemFacade() {
        dashBoard = Utility.setDashboard(100, 5, systemName);
        mainMenu = new Menu();
        services = new Services();
        systems = new ArrayList<>();
    }

    /**
     * add a sub-system to the system list
     *
     * @param system sub-system
     */
    public void addSubSystem(CovidBAndTSystem system) {
        systems.add(system);
    }

    /**
     * set the actor(like agent for real actor) for the system
     *
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
            services.clear();
            for (CovidBAndTSystem system : systems) {
                services.add(new GoSubsystemService(system));
            }


            Service service = mainMenu.showMenu(actor, services);
            String result = null;

            result = service.execute(actor);

            System.out.println(result);
        }
    }
}
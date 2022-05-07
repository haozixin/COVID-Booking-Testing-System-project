package subSystems;


import engine.Service;
import engine.Services;
import models.Actor;
import utility.Utility;
import webServiceAPI.ServicesAdapter;
import webServiceAPI.WebServicesTarget;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Abstract class for Subsystems
 * functions are control the whole subsystem running and the actions and menu displaying
 */
public abstract class CovidBAndTSystem {
    protected String systemName;
    protected WebServicesTarget serviceForUser;
    protected ArrayList<String> dashBoard;
    protected Actor actor;
    protected Map<Actor, Service> lastActionMap = new HashMap<Actor, Service>();
    // is the actions list in menu that actors will be able to choose from
    protected Services services;

    public void addUser(Actor actor) {
        this.actor = actor;
    }

    /**
     * Common constructor for child classes
     * Initialize elements of the subsystem that would be used in the running of the system
     */
    public CovidBAndTSystem() {
        serviceForUser = new ServicesAdapter();
        services = new Services();
    }

    /**
     * the core of subsystem
     */
    public void run() {
        if (actor == null)
            throw new IllegalStateException();

        // if the actor wants to go back, then the loop will end
        while (!actor.wantsGoBack()) {
            showDashBoard();
            processActorTurn(actor);
        }
    }

    /**
     * Get the name of the subsystem
     *
     * @return
     */
    public String getSystemName() {
        return systemName;
    }

    /**
     * Display the dash board for the subsystem
     */
    protected void showDashBoard() {
        Utility.printArrayList(dashBoard);
    }

    protected void setDashBoard() {
        dashBoard = Utility.setDashboard(90, 5, systemName);
    }

    /**
     * arrange the actions(user could choose from menu) for the actor here
     *
     * @param actor the actor who is going to take the turn
     */
    protected void processActorTurn(Actor actor) {

        Service service = actor.playTurn(services, lastActionMap.get(actor));
        lastActionMap.put(actor, service);

        String result = null;

        result = service.execute(actor);

        System.out.println(result);
    }
}
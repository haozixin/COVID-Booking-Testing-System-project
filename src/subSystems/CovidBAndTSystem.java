package subSystems;



import engine.Action;
import engine.Actions;
import models.Actor;
import utility.Utility;
import webServiceAPI.ServicesAdapter;
import webServiceAPI.WebServicesTarget;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * Abstract class for Subsystems
 * functions are control the whole subsystem running and the actions and menu displaying
 */
public abstract class CovidBAndTSystem {
    protected String systemName;
    protected WebServicesTarget serviceForUser;
    protected ArrayList<String> dashBoard;
    protected Actor actor;
    protected Map<Actor, Action> lastActionMap = new HashMap<Actor, Action>();
    // is the actions list in menu that actors will be able to choose from
    protected Actions actions;

    public void addUser(Actor actor){
        this.actor = actor;
    }

    /**
     * Common constructor for child classes
     * Initialize elements of the subsystem that would be used in the running of the system
     */
    public CovidBAndTSystem() {
        serviceForUser = new ServicesAdapter();
        actions = new Actions();
    }

    /**
     * the core of subsystem
     */
    public void run(){
        if (actor == null)
            throw new IllegalStateException();

        // if the actor wants to go back, then the loop will end
        while (true) {
            showDashBoard();
            processActorTurn(actor);
        }
    }

    /**
     * Get the name of the subsystem
     * @return
     */
    public String getSystemName(){
        return systemName;
    }

    /**
     * Display the dash board for the subsystem
     */
    protected void showDashBoard() {
        Utility.printArrayList(dashBoard);
    }

    protected void setDashBoard(){
        dashBoard = Utility.setDashboard(90,5, systemName);
    }

    /**
     * arrange the actions(user could choose from menu) for the actor here
     * @param actor the actor who is going to take the turn
     */
    protected void processActorTurn(Actor actor){

        Action action = actor.playTurn(actions, lastActionMap.get(actor));
        lastActionMap.put(actor, action);

        String result = null;
        try {
            result = action.execute(actor);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(result);
    }
}
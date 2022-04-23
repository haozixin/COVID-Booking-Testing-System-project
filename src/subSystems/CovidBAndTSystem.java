package subSystems;

import actions.GoBackAction;
import engine.Display;
import engine.actions.Action;
import engine.actions.Actions;
import actors.Actor;
import utility.Utility;
import webServiceAPI.ServicesAdapter;
import webServiceAPI.WebServicesTarget;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Facade for all subsystem
 *
 * Each subsystem interacts with each other in such a way, just as in a smart home,
 * when the central controller is switched to "movie mode", the lights are dimmed, the curtains are closed, the TV is turned on, etc.
 * (a method of each system is called)
 */
public abstract class CovidBAndTSystem {
    protected String systemName;
    protected WebServicesTarget serviceForUser;
    protected ArrayList<String> dashBoard;
    protected Actor actor;
    protected Display display;
    protected Map<Actor, Action> lastActionMap = new HashMap<Actor, Action>();
    // is the actions list in menu that actors will be able to choose from
    protected Actions actions;

    public void addUser(Actor actor){
        this.actor = actor;
    }

    public CovidBAndTSystem() {
        display = new Display();
        serviceForUser = new ServicesAdapter();
        actions = new Actions();
    }

    public abstract void run();

    public String getSystemName(){
        return systemName;
    }

    //TODO: change name
    public void display() {
        Utility.printArrayList(dashBoard);
    }

    protected void processActorTurn(Actor actor){

        Action action = actor.playTurn(actions, lastActionMap.get(actor), display);
        lastActionMap.put(actor, action);

        String result = null;
        try {
            result = action.execute(actor);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        display.println(result);
    }


}

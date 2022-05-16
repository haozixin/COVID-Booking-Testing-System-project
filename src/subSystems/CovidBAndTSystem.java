package subSystems;


import engine.Service;
import engine.Services;
import engine.CurrentOperator;
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
    protected CurrentOperator currentOperator;
    protected Map<CurrentOperator, Service> lastActionMap = new HashMap<CurrentOperator, Service>();
    // is the actions list in menu that actors will be able to choose from
    protected Services services;

    public void addUser(CurrentOperator currentOperator) {
        this.currentOperator = currentOperator;
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
        if (currentOperator == null)
            throw new IllegalStateException();

        // if the actor wants to go back, then the loop will end
        while (!currentOperator.wantsGoBack()) {
            showDashBoard();
            processActorTurn(currentOperator);
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
     * @param currentOperator the actor who is going to take the turn
     */
    protected void processActorTurn(CurrentOperator currentOperator) {

        Service service = currentOperator.playTurn(services, lastActionMap.get(currentOperator));
        lastActionMap.put(currentOperator, service);

        String result = null;

        result = service.execute(currentOperator);

        System.out.println(result);
    }
}
package services;


import engine.Service;
import engine.CurrentOperator;
import subSystems.CovidBAndTSystem;

/**
 * Action to go to the CovidBAndTSystem(Subsystem)
 */
public class GoSubsystemService extends Service {
    private CovidBAndTSystem covidBAndTSystem;

    public GoSubsystemService(CovidBAndTSystem covidBAndTSystem) {
        this.covidBAndTSystem = covidBAndTSystem;
    }
    @Override
    public String execute(CurrentOperator currentOperator){
        // because the attribute of wantsGoBack is set true when the user click go back button,
        // we have to reset it to false when the user chose this action before the subsystem runs, otherwise, the user cannot go into the subsystem.
        currentOperator.setWantsGoBack(false);

        covidBAndTSystem.addUser(currentOperator);
        covidBAndTSystem.run();
        return "You are in the main menu";
    }

    @Override
    public String menuDescription(CurrentOperator currentOperator) {
        return "Go to "+covidBAndTSystem.getSystemName();
    }
}

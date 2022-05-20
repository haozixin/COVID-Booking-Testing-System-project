package services;


import engine.Service;
import engine.CurrentOperator;

/**
 * Action to go back to the previous room.
 */
public class GoBackService extends Service {
    private CurrentOperator currentOperator;

    /**
     * Constructor.
     */
    public GoBackService() {
        currentOperator = CurrentOperator.getInstance();
    }

    @Override
    public String execute(CurrentOperator currentOperator) {
        currentOperator.setWantsGoBack(true);
        return menuDescription(currentOperator);
    }

    @Override
    public String menuDescription(CurrentOperator currentOperator) {
        return "Go back to parent menu";
    }

}

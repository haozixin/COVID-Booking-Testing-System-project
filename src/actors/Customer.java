package actors;

import engine.Display;
import engine.Menu;
import engine.actions.Action;
import engine.actions.Actions;

/**
 * current user of the system
 * login as customer
 */
public class Customer extends Actor {
    private final Menu menu = new Menu();

    @Override
    public Action playTurn(Actions actions, Action lastAction, Display display) {

        // Handle multi-turn Actions
        if (lastAction != null && lastAction.getNextAction() != null){
            return lastAction.getNextAction();
        }

        return menu.showMenu(this, actions, display);
    }
}

package actors;

import engine.Display;
import engine.actions.Action;
import engine.actions.Actions;

/**
 * This class is like an agent that can be used by actors to contact with the system.(will be more security)
 * The current user is the user that is currently logged in. (might be the resident or the Administrators/receptionist ...)
 */
public abstract class Actor{

    // class name
    public static final String name = "CurrentUser";
    // to judge if the user is logged in or not
    protected boolean isLogged = false;

    protected String token;

    public void setLogged(boolean logged) {
        this.isLogged = logged;
    }

    public boolean getLogged(){
        return isLogged;
    }

    public void setToken(String token) {
        this.token = token;
    }

    //    public String convertToJson() {
//        // 将Java对象转换为JSON对象
//        ObjectMapper mapper = new ObjectMapper();
//        String json = null;
//        try {
//            json = mapper.writeValueAsString(this);
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }
//        return json;
//

    /**
     * Select and return an action to perform on the current turn.
     *
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param display    the I/O object to which messages may be written
     * @return the Action to be performed
     */
    public abstract Action playTurn(Actions actions, Action lastAction,  Display display);




}

package actors;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import engine.Display;
import engine.Menu;
import engine.actions.Action;
import engine.actions.Actions;
import users.User;

import java.util.Base64;
import java.util.HashSet;
import java.util.Set;

/**
 * This class is like an agent that can be used by actors to contact with the system.(will be more security)
 * The current user is the user that is currently logged in. (might be the resident or the Administrators/receptionist ...)
 */
public class Actor{
    // singleton
    private static Actor instance;
    private Menu menu;
    private Set<String> roles = new HashSet<>();
    // class name
    public static final String name = "CurrentUser";
    // to judge if the user is logged in or not
    // true is logged in, false is not logged in
    private boolean isLogged;



    // to judge if the user wants to go back to the previous menu
    private boolean wantsGoBack;
    // when the user is logged in, he/she will get the token from the server
    private String token;

    public Actor() {
        menu = new Menu();
        isLogged = false;
        wantsGoBack = false;
    }

    /**
     *  set the current user's information that we get from the token
     * @param token
     */
    public void setDetailInfo(String token) {
        this.token = token;
    }

    public void setLoginState(boolean logged) {
        this.isLogged = logged;
    }

    public boolean getLoginState(){
        return isLogged;
    }

    public void setToken(String token) {
        this.token = token;
    }


    public static Actor getInstance(){
        if(instance == null){
            instance = new Actor();
        }
        return instance;
    }

    /**
     * Select and return an action to perform on the current turn.
     *
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param display    the I/O object to which messages may be written
     * @return the Action to be performed
     */
    public Action playTurn(Actions actions, Action lastAction, Display display) {

        // Handle multi-turn Actions
        if (lastAction != null && lastAction.getNextAction() != null){
            return lastAction.getNextAction();
        }
        return menu.showMenu(this, actions, display);
    }

    /**
     *  Return a JSON representation of this Actor （information from token）
     * @param token the token of the user
     * @return  a JSON representation of this Actor
     */
    private ObjectNode parseToken(String token) throws JsonProcessingException {
        String[] tokenArr = token.split("\\.");
        String base64Str = tokenArr[1];
        String jsonStr = new String(Base64.getDecoder().decode(base64Str));
        ObjectNode jsonObject = new ObjectMapper().readValue(jsonStr, ObjectNode.class);
        return jsonObject;
    }

    public void getRoleFromToken() throws JsonProcessingException {

        ObjectNode jsonObject = parseToken(token);
        for (String value : User.keyToRoleMap.values()) {
            boolean isTheRole = jsonObject.get(value).asBoolean();
            if (isTheRole) {
                roles.add(value);
            }
        }
    }



    public void setWantsGoBack(boolean wantsGoBack) {
        this.wantsGoBack = wantsGoBack;
    }

    public boolean getIsGoBack() {
        return wantsGoBack;
    }
}

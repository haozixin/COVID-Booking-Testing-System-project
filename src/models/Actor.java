package models;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import engine.Service;
import engine.Services;
import engine.Menu;
import enums.UserRoles;
import webServiceAPI.ServicesAdapter;
import webServiceAPI.WebServicesTarget;

import java.io.IOException;
import java.util.Base64;
import java.util.HashSet;
import java.util.Set;

/**
 * This class is like an agent that can be used by actors to contact with the system.(will be more security)
 * The current user is the user that is currently logged in. (might be the resident or the Administrators/receptionist ...)
 */
public class Actor extends Model{
    public static final String ID_IN_TOKEN = "sub";
    private WebServicesTarget webServices;
    private static Actor instance;
    // to judge if the user is logged in or not
    // true is logged in, false is not logged in
    private boolean isLogged;
    // when the user is logged in, he/she will get the token from the server
    private String token;
    // to judge if the user wants to go back to the previous menu
    private boolean wantsGoBack;
    private Menu menu = new Menu();
    private Set<String> roles = new HashSet<>();

    private Actor() {
        webServices = new ServicesAdapter();
    }

    public boolean isCustomer(){
        return roles.contains(UserRoles.IS_CUSTOMER_FIELD.getName());
    }

    public boolean isAdministrator(){
        return roles.contains(UserRoles.IS_ADMIN_FIELD.getName());
    }
    public boolean isHealthcareWorker(){
        return roles.contains(UserRoles.IS_HEALTHCARE_WORKER_FIELD.getName());
    }

    /**
     * Select and return an action to perform on the current turn.
     *
     * @param services    collection of possible Actions for this Actor
     * @param lastService The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @return the Action to be performed
     */
    public Service playTurn(Services services, Service lastService) {

        // Handle multi-turn Actions
        if (lastService != null && lastService.getNextAction() != null){
            return lastService.getNextAction();
        }
        return menu.showMenu(this, services);
    }

    private void setLoginState(boolean logged) {
        this.isLogged = logged;
    }

    /**
     *  Return a JSON representation of this Actor (information from token)
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

    public static Actor getInstance(){
        if(instance == null){
            instance = new Actor();
        }
        return instance;
    }

    private void getRoleFromToken() throws JsonProcessingException {

        ObjectNode jsonObject = parseToken(token);
        for (String value : UserRoles.getAllRoles()) {
            boolean isTheRole = jsonObject.get(value).asBoolean();
            if (isTheRole) {
                roles.add(value);
            }
        }
    }

    private void setToken(String token) {
        this.token = token;
        try {
            getRoleFromToken();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    public String getName() {
        ObjectNode jsonObject = null;
        try {
            jsonObject = parseToken(token);
            return jsonObject.get("username").asText();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }

    }

    public boolean isLoggedIn(){
        return isLogged;
    }

    public boolean login(String userName, String password) throws IOException, InterruptedException {
        boolean hasLogged = false;
        String token = webServices.getToken(userName, password);
        boolean result = webServices.verifyToken(token);
        if (result) {
            // if login success, set state to logged in
            this.setLoginState(true);
            // store token
            this.setToken(token);
            hasLogged = true;
            isLogged = true;
        }
        return hasLogged;
    }


    public void setWantsGoBack(boolean wantsGoBack) {
        this.wantsGoBack = wantsGoBack;
    }

    public boolean wantsGoBack(){
        return wantsGoBack;
    }

    public String getIdFromToken() {
        ObjectNode jsonObject = null;
        try {
            jsonObject = parseToken(token);
            return jsonObject.get(ID_IN_TOKEN).asText();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }


}

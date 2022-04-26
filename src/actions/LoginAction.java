package actions;

import actors.Actor;
import engine.actions.Action;
import utility.Utility;
import webServiceAPI.ServicesAdapter;
import webServiceAPI.WebServicesTarget;

import java.io.IOException;
import java.util.Scanner;

public class LoginAction extends Action {
    private WebServicesTarget webServicesTarget;
    String token;
    String name;


    /**
     * Constructor
     */
    public LoginAction() {
        token = null;
        name = "Login system operation";
        webServicesTarget = new ServicesAdapter();

    }




    @Override
    public String execute(Actor actor) throws IOException, InterruptedException {
        // display where the user is now
        Utility.displayAction(name);

        boolean result = authentication(webServicesTarget);

        if (result) {
            // if login success, set state to logged in
            actor.setLoginState(true);
            // store token
            actor.setToken(token);
            return "successful login";
        } else {
            return "failed login, please try again";
        }
    }

    private boolean authentication(WebServicesTarget serviceForUser) throws IOException, InterruptedException {
        String userName, password;

        System.out.print("Input your userName: ");
        Scanner s = new Scanner(System.in);
        userName = s.next();

        System.out.print("Input your password: ");
        password = s.next();

        // get token
        try{
            token = serviceForUser.getToken(userName, password);
        } catch (NullPointerException e){
            System.out.println("Invalid userName or password");
        }



        // verify token and return status code
        return serviceForUser.verifyToken(token);
    }




    @Override
    public String menuDescription(Actor actor) {
        return "Go to Login";
    }
}

package actions;

import actors.Actor;
import engine.actions.Action;
import enums.ResponseStatus;
import utility.Utility;
import webServiceAPI.Services;
import webServiceAPI.WebServices;

import java.io.IOException;
import java.util.Scanner;

public class Login extends Action {
    private WebServices webServices = new Services();
    String token;


    /**
     * Constructor
     */
    public Login() {
    }


    @Override
    public String execute(Actor actor) throws IOException, InterruptedException {
        // display where the user is now
        display();

        int code = authentication(webServices);

        if (code == ResponseStatus.CODE_200.getCode()) {
            // if login success, set state to logged in
            actor.setLogged(true);
            // store token
            actor.setToken(token);
            return "successful login";
        } else {
            return "failed login, please try again";
        }
    }

    private int authentication(WebServices serviceForUser) throws IOException, InterruptedException {
        String userName, password;

        System.out.print("Input your userName: ");
        Scanner s = new Scanner(System.in);
        userName = s.next();

        System.out.print("Input your password: ");
        password = s.next();

        // get token
        token = serviceForUser.getToken(userName, password);


        // verify token and return status code
        return serviceForUser.verifyToken(token);
    }

    private void display() {
        System.out.println("----------You are doing Login system operation---------");
    }



    @Override
    public String menuDescription(Actor actor) {
        return "Go to Login";
    }
}

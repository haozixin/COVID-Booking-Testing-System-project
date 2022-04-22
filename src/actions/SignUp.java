package actions;

import actors.Actor;
import com.fasterxml.jackson.databind.node.ObjectNode;
import engine.actions.Action;
import enums.Path;
import enums.ResponseStatus;
import user.User;
import webServiceAPI.Services;
import webServiceAPI.WebServices;

import java.io.IOException;

public class SignUp extends Action {
    private WebServices webServices = new Services();
    private User user;


    @Override
    public String execute(Actor actor) throws IOException, InterruptedException {
        display();

        user = new User();
        String jsonNodes = user.buildRequestBody();
        int code =  webServices.postData(Path.SIGN_UP.getPath(), jsonNodes);
        System.out.println("Details: "+user);
        if (code == ResponseStatus.CODE_201.getCode()) {
            return "You have successfully signed up";
        }
        return null;
    }

    private void display() {
        System.out.println("----------You are doing Sign Up operation---------");
    }

    @Override
    public String menuDescription(Actor actor) {
        return "Go to Sign Up";
    }
}

package actions;

import actors.Actor;
import com.fasterxml.jackson.databind.node.ObjectNode;
import engine.Entity;
import engine.actions.Action;
import enums.Path;
import enums.ResponseStatus;
import users.User;
import utility.Utility;
import webServiceAPI.ServicesAdapter;
import webServiceAPI.WebServicesTarget;

import java.io.IOException;

public class SignUpAction extends Action {
    private WebServicesTarget webServicesTarget;
    private Entity user;

    public SignUpAction() {
        webServicesTarget = new ServicesAdapter();

        name = "Sign Up operation";
    }

    @Override
    public String execute(Actor actor) throws IOException, InterruptedException {
        Utility.displayAction(name);
        String jsonNode = User.createNewEntity();

        ObjectNode node = webServicesTarget.postData(Path.SIGN_UP.getPath(), jsonNode);
        System.out.println("Details: "+user);
        if (node!=null) {
            return "You have successfully signed up";
        }
        return null;
    }



    @Override
    public String menuDescription(Actor actor) {
        return "Go to Sign Up";
    }
}

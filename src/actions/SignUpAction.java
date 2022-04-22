package actions;

import actors.Actor;
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
    private User user;

    public SignUpAction() {
        webServicesTarget = new ServicesAdapter();
        user = new User();
        name = "Sign Up operation";
    }

    @Override
    public String execute(Actor actor) throws IOException, InterruptedException {
        Utility.displayAction(name);

        String jsonNodes = user.buildRequestBody();
        boolean code = webServicesTarget.postData(Path.SIGN_UP.getPath(), jsonNodes);
        System.out.println("Details: "+user);
        if (code) {
            return "You have successfully signed up";
        }
        return null;
    }



    @Override
    public String menuDescription(Actor actor) {
        return "Go to Sign Up";
    }
}

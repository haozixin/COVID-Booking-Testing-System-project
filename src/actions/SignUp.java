package actions;

import actors.Actor;
import engine.actions.Action;
import enums.Path;
import enums.ResponseStatus;
import user.User;
import webServiceAPI.ServicesAdapter;
import webServiceAPI.WebServicesTarget;

import java.io.IOException;

public class SignUp extends Action {
    private WebServicesTarget webServicesTarget = new ServicesAdapter();
    private User user;


    @Override
    public String execute(Actor actor) throws IOException, InterruptedException {
        display();

        user = new User();
        String jsonNodes = user.buildRequestBody();
        boolean code = webServicesTarget.postData(Path.SIGN_UP.getPath(), jsonNodes);
        System.out.println("Details: "+user);
        if (code) {
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

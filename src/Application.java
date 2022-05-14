import models.Actor;
import subSystems.*;

/**
 * @Author: Zixin Hao, Jayden Yang
 * @Date: 2022-4-26
 * @Version: 1.0
 * @Class: Application
 * @Description: The main class of the application.
 * @Function List: see the check_list document in the doc folder.
 *
 */
public class Application {

    public static void main(String[] args) {


        Actor actor = Actor.getInstance();
        SystemFacade systemFacade = new SystemFacade();
        systemFacade.addActor(actor);
        systemFacade.addSubSystem(new LoginSubsystem());
        systemFacade.addSubSystem(new OnsiteTestingSubsystem());
        systemFacade.addSubSystem(new SearchForSitesSubsystem());
        systemFacade.addSubSystem(new OnsiteBookingSubsystem());
        systemFacade.addSubSystem(new HomeBookingSubsystem());
        systemFacade.run();

//        Actor actorModel = Actor.getInstance();
//        LoginView loginView = new LoginView(actorModel);
//        LoginController controller = new LoginController(loginView, actorModel);
//        loginView.update();
//        loginView.setVisible(true);


//        User userModel = new User();
//        SignUpView signUpView = new SignUpView(userModel);
//        SignUpController controller = new SignUpController(signUpView, userModel);
//        signUpView.update();
//        signUpView.setVisible(true);


    }
}

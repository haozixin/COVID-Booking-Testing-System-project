import controllers.LoginController;
import controllers.SignUpController;
import models.Actor;
import models.User;
import subSystems.*;
import views.LoginView;
import views.SignUpView;

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
        MainSystem mainSystem = new MainSystem();
        mainSystem.addActor(actor);
        mainSystem.addSubSystem(new LoginSubsystem());
        mainSystem.addSubSystem(new OnsiteTestingSubsystem());
        mainSystem.addSubSystem(new SearchForSitesSubsystem());
        mainSystem.addSubSystem(new OnsiteBookingSubsystem());
        mainSystem.run();

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

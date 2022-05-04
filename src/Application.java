import controllers.LoginController;
import models.Actor;
import views.LoginView;

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
        Actor actorModel = Actor.getInstance();
        LoginView loginView = new LoginView(actorModel);
        LoginController controller = new LoginController(loginView, actorModel);
        loginView.update();
        loginView.setVisible(true);


    }
}

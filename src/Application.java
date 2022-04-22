import actors.Actor;
import actors.Customer;
import subSystems.CovidBAndTSystem;
import subSystems.LoginSubsystem;

import java.io.IOException;

public class Application {
    public static void main(String[] args) throws IOException, InterruptedException {

        Actor actor = new Customer();
//        actor.setLogged(false);
        CovidBAndTSystem loginSubsystem = new LoginSubsystem();

        loginSubsystem.addUser(actor);



        loginSubsystem.run();






    }

}

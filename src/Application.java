import actors.Actor;
import engine.DataPublisher;
import subSystems.CovidBAndTSystem;
import subSystems.LoginSubsystem;
import subSystems.SearchForSitesSubsystem;
import testingSite.SitesCollection;

import java.io.IOException;

public class Application {
    public static void main(String[] args) throws IOException, InterruptedException {

//        Actor actor = Actor.getInstance();
//        CovidBAndTSystem loginSubsystem = new LoginSubsystem();
//        loginSubsystem.addUser(actor);
//        loginSubsystem.run();

        Actor actor = Actor.getInstance();
        DataPublisher dataPublisher = new DataPublisher();
        dataPublisher.subscribe(SitesCollection.getInstance());
        dataPublisher.notifySubscribers();

        CovidBAndTSystem searchForSitesSubsystem = new SearchForSitesSubsystem();
        searchForSitesSubsystem.addUser(actor);
        searchForSitesSubsystem.run();




    }

}

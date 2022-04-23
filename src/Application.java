import actors.Actor;
import engine.DataPublisher;
import enums.UserRoles;
import subSystems.CovidBAndTSystem;
import subSystems.HomeBookingSubsystem;
import subSystems.LoginSubsystem;
import subSystems.SearchForSitesSubsystem;
import testingSites.SitesCollection;

import java.io.IOException;

public class Application {
    public static void main(String[] args) throws IOException, InterruptedException {

//        Actor actor = Actor.getInstance();
//        CovidBAndTSystem loginSubsystem = new LoginSubsystem();
//        loginSubsystem.addUser(actor);
//        loginSubsystem.run();



//        Actor actor = Actor.getInstance();
//        DataPublisher dataPublisher = new DataPublisher();
//        dataPublisher.subscribe(SitesCollection.getInstance());
//        dataPublisher.notifySubscribers();
//
//        CovidBAndTSystem searchForSitesSubsystem = new SearchForSitesSubsystem();
//        searchForSitesSubsystem.addUser(actor);
//        searchForSitesSubsystem.run();


        // create a new and single actor
        Actor actor = Actor.getInstance();

        // create a new and single data publisher
        DataPublisher dataPublisher = new DataPublisher();
        // add subscriber to the data publisher
        dataPublisher.subscribe(SitesCollection.getInstance());
        // ...(more in the future)

        // notify the data publisher
        dataPublisher.notifySubscribers();

        // create a new COVID Booking & Testing System (main system)
        MainSystem mainSystem = new MainSystem();
        // add user to the main system
        mainSystem.setActor(actor);

        mainSystem.addSubSystem(new LoginSubsystem());
        mainSystem.addSubSystem(new SearchForSitesSubsystem());
        mainSystem.addSubSystem(new HomeBookingSubsystem());
        // ...(more in the future)




        mainSystem.run();





    }

}

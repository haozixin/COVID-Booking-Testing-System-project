import actors.Actor;
import engine.DataPublisher;
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
        //----------------------------------------------------------------------------------------------------------------------


        // step 1  - create elements
        // create a new and single actor
        Actor actor = Actor.getInstance();
        // create a new COVID Booking & Testing System (main system)
        MainSystem mainSystem = new MainSystem();
        // create a new and single data publisher
        DataPublisher dataPublisher = new DataPublisher();


        // step 2 - add subscriber to the publisher and do initial notify
        // add subscriber to the data publisher
        dataPublisher.subscribe(SitesCollection.getInstance());
        // ...(more in the future)(easier to extend)

        // notify the data publisher
        dataPublisher.notifySubscribers();


        // step 3 - add user/actor and subsystems to the main system
        // add user to the main system
        mainSystem.addActor(actor);

        mainSystem.addSubSystem(new LoginSubsystem());
        mainSystem.addSubSystem(new SearchForSitesSubsystem());
        mainSystem.addSubSystem(new HomeBookingSubsystem());
        // ...(more in the future)(easier to extend)

        // step 4 - run the main system
        mainSystem.run();


    }

}

import actors.Actor;
import bookings.BookingsCollection;
import engine.DataPublisher;
import subSystems.*;
import testingSites.SitesCollection;
import users.UsersCollection;

import java.io.IOException;

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
    /**
     * The main method of the application.
     * Program entrance.
     * @param args
     * @throws IOException
     * @throws InterruptedException
     */
    public static void main(String[] args) throws IOException, InterruptedException {
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
        dataPublisher.subscribe(BookingsCollection.getInstance());
        dataPublisher.subscribe(UsersCollection.getInstance());
        // ...(more in the future)(easier to extend)

        // notify the data publisher
        dataPublisher.notifySubscribers();


        // step 3 - add user/actor and subsystems to the main system
        // add user to the main system
        mainSystem.addActor(actor);

        mainSystem.addSubSystem(new LoginSubsystem());
        mainSystem.addSubSystem(new SearchForSitesSubsystem());
        mainSystem.addSubSystem(new OnsiteBookingSubsystem());
        mainSystem.addSubSystem(new OnsiteTestingSubsystem());
        mainSystem.addSubSystem(new HomeBookingSubsystem());
        // ...(more in the future)(easier to extend)

        // step 4 - run the main system
        mainSystem.run();
    }
}

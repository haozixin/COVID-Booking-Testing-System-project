import com.fasterxml.jackson.core.JsonProcessingException;
import engine.CurrentOperator;
import engine.adminNotification.BookingPublisher;
import engine.adminNotification.Publisher;
import subSystems.*;

/**
 * @Author: Zixin Hao, Jayden Yang
 * @Date: 2022-4-26
 * @Version: 1.0
 * @Class: Application
 * @Description: The main class of the application.
 * @Function List: see the check_list document in the doc folder.
 */
public class Application {

    public static void main(String[] args) throws JsonProcessingException {


 // Application

        Publisher publisher = BookingPublisher.getInstance();


        CurrentOperator currentOperator = CurrentOperator.getInstance();


        SystemFacade systemFacade = new SystemFacade();
        systemFacade.addActor(currentOperator);
        systemFacade.addSubSystem(new LoginSubsystem());
        systemFacade.addSubSystem(new OnsiteTestingSubsystem());
        systemFacade.addSubSystem(new SearchForSitesSubsystem());
        systemFacade.addSubSystem(new OnsiteBookingSubsystem());
        systemFacade.addSubSystem(new HomeBookingSubsystem());


        systemFacade.run();


    }
}

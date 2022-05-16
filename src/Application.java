import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import engine.CurrentOperator;
import mementos.BookingMemento;
import mementos.Caretaker;
import models.BookingModel;
import models.HomeBookingModel;
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

        // test Memento pattern
//        ObjectNode objectNode;
//        String json2 = "{\"name\":\"Allen1\",\"age\":18,\"set\":{\"tel\":1591851786568, \"add\":\"always at home\"}}";
//
//        objectNode = new ObjectMapper().readValue(json2, ObjectNode.class);
//
//
//        BookingModel bookingModel = new HomeBookingModel();
//        Caretaker caretaker = new Caretaker();
//        bookingModel.setState(objectNode);
//        caretaker.addMemento(bookingModel.save());
//        System.out.println(bookingModel.getState());
//
//        ObjectNode objectNode2;
//        String json3 = "{\"name\":\"Allen\",\"age\":20,\"set\":{\"tel\":1591851786568, \"add\":\"always at home\"}}";
//        objectNode2 = new ObjectMapper().readValue(json3, ObjectNode.class);
//
//        bookingModel.setState(objectNode2);
//        System.out.println(bookingModel.getState());
//
//        caretaker.undo();
//        System.out.println(bookingModel.getState());


//        CurrentOperator currentOperator = CurrentOperator.getInstance();
//        SystemFacade systemFacade = new SystemFacade();
//        systemFacade.addActor(currentOperator);
//        systemFacade.addSubSystem(new LoginSubsystem());
//        systemFacade.addSubSystem(new OnsiteTestingSubsystem());
//        systemFacade.addSubSystem(new SearchForSitesSubsystem());
//        systemFacade.addSubSystem(new OnsiteBookingSubsystem());
//        systemFacade.addSubSystem(new HomeBookingSubsystem());
//        systemFacade.run();

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

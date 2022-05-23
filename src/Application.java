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

        // test Memento pattern
//        ObjectNode objectNode;
//        String json2 = "{\"name\":\"Allen1\",\"age\":18,\"set\":{\"tel\":1591851786568, \"add\":\"always at home\"}}";
//
//        objectNode = new ObjectMapper().readValue(json2, ObjectNode.class);
//
//
//        BookingModel bookingModel = new HomeBookingModel();
        // 创建管理员
//        Caretaker caretaker = new Caretaker();

//        bookingModel.setState(objectNode);
        // 创建备忘录
//        caretaker.addMemento(bookingModel.save());
//        System.out.println(bookingModel.getState());
//
//        ObjectNode objectNode2;
//        String json3 = "{\"name\":\"Allen\",\"age\":20,\"set\":{\"tel\":1591851786568, \"add\":\"always at home\"}}";
//        objectNode2 = new ObjectMapper().readValue(json3, ObjectNode.class);
//
        // 改变状态
//        bookingModel.setState(objectNode2);
//        System.out.println(bookingModel.getState());
//
        // 恢复状态
//        caretaker.undo();
//        System.out.println(bookingModel.getState());

 // Application

//        Publisher publisher = BookingPublisher.getInstance();


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

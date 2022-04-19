import enums.Path;
import loginSubsystem.LoginSubsystem;
import webServiceAPI.Services;
import webServiceAPI.WebServices;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Application {
    public static void main(String[] args) throws IOException, InterruptedException {

//        WebServices service = new Services();
//        Booking booking = new Booking("f3dc6047-1e2f-4c65-909f-37189cfd98df","7fbd25ee-5b64-4720-b1f6-4f6d4731260e","Testdata2", "2022-01-01 12:00:00");
//        String jsonString = booking.buildRequestBody();
//        int responseNumber = service.postData(Path.BOOKING.getPath(), jsonString);
//        System.out.println("In ServiceTest : " + responseNumber);

        //TODO: 需要设计确定整个情景的business process ， 做简单的GUI
// 1
//        // system start/initial
//        CovidBAndTSystem system = new CovidBAndTSystem();
//        try {
//            system.login();
//        } catch (IOException | InterruptedException e) {
//            e.printStackTrace();
//        }

//        // 2
//        CovidBAndTSystem system = new CovidBAndTSystem();
//        IWebServices servicesForSites = new ServicesForSites();
//        try {
//            servicesForSites.getTestingSites();
//        } catch (IOException | InterruptedException e) {
//            e.printStackTrace();
//        }

        String a = "Allen";
        String b = "Allen";

        if (a.equals(b)) {
            System.out.println("Kanye");
        }







    }

}

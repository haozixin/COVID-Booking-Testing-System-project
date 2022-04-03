import SystemFacade.CovidBAndTSystem;
import WebServiceAPI.IWebServices;
import WebServiceAPI.ServicesForSites;

import java.io.IOException;
import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
// 1
//        // system start/initial
//        CovidBAndTSystem system = new CovidBAndTSystem();
//        try {
//            system.login();
//        } catch (IOException | InterruptedException e) {
//            e.printStackTrace();
//        }

        // 2
        CovidBAndTSystem system = new CovidBAndTSystem();
        IWebServices servicesForSites = new ServicesForSites();
        try {
            servicesForSites.getTestingSites();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }






    }

}

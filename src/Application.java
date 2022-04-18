
import com.fasterxml.jackson.databind.ObjectMapper;

import enums.Path;
import loginSubsystem.LoginSubsystem;
import searchForSitesSubsystem.TestingSite;
import users.CurrentUser;
import users.Customer;
import webServiceAPI.Services;
import webServiceAPI.WebServices;


import java.io.IOException;


public class Application {
    public static void main(String[] args) throws IOException, InterruptedException {

        LoginSubsystem loginSubsystem = new LoginSubsystem(new Services());
        loginSubsystem.login();

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










    }

}

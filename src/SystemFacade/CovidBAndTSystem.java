package SystemFacade;

import HomeBookingSubsystem.HomeBookingSubsystem;
import LoginSubsystem.LoginSubsystem;
import OnsiteBookingSubsystem.OnsiteBookingSubsystem;
import OnsiteTestingSubsystem.OnsiteTestingSubsystem;
import SearchForSitesSubsystem.SearchForSitesSubsystem;
import WebServiceAPI.IWebServices;
import WebServiceAPI.ServicesForUser;
import WebServiceAPI.WebServices;

import java.io.IOException;
import java.util.Scanner;

/**
 * Facade for all subsystem
 */
public class CovidBAndTSystem {

    private LoginSubsystem loginSubsystem;
    private HomeBookingSubsystem homeBookingSubsystem;
    private OnsiteBookingSubsystem onsiteBookingSubsystem;
    private OnsiteTestingSubsystem onsiteTestingSubsystem;
    private SearchForSitesSubsystem searchForSitesSubsystem;


    /**
     * Constructor
     */
    public CovidBAndTSystem() {
        startSystem();
        this.loginSubsystem = new LoginSubsystem(new ServicesForUser());
    }

    private void startSystem(){
        System.out.print("Input your API-key to initial the system: ");
        Scanner s = new Scanner(System.in);
        String key = s.next();
        WebServices.setKey(key);
    }

    public void login() throws IOException, InterruptedException {
        this.loginSubsystem = new LoginSubsystem(new ServicesForUser());
        loginSubsystem.login();
    }

    /**
     * Sample code
     */
    public void function(){
        System.out.println("I am Facade");
        System.out.println("----------");
    }

    //TODO: GUI - 用户交互界面




}

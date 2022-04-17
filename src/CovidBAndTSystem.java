import bookingSubsystem.HomeBookingSubsystem;
import loginSubsystem.LoginSubsystem;
import bookingSubsystem.OnsiteBookingSubsystem;
import onsiteTestingSubsystem.OnsiteTestingSubsystem;
import searchForSitesSubsystem.SearchForSitesSubsystem;
import webServiceAPI.Services;
import webServiceAPI.WebServices;

import java.io.IOException;
import java.util.Scanner;

/**
 * Facade for all subsystem
 *
 * Each subsystem interacts with each other in such a way, just as in a smart home,
 * when the central controller is switched to "movie mode", the lights are dimmed, the curtains are closed, the TV is turned on, etc.
 * (a method of each system is called)
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
        this.loginSubsystem = new LoginSubsystem(new Services());
    }

    private void startSystem(){

    }

    public void login() throws IOException, InterruptedException {
        this.loginSubsystem = new LoginSubsystem(new Services());
        loginSubsystem.login();
    }

    /**
     * Sample code
     */
    public void function(){
        System.out.println("I am Facade");
        System.out.println("----------");
    }






}

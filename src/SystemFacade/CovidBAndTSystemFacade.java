package SystemFacade;

import HomeBookingSubsystem.HomeBookingSubsystem;
import LoginSubsystem.LoginSubsystem;
import OnsiteBookingSubsystem.OnsiteBookingSubsystem;
import OnsiteTestingSubsystem.OnsiteTestingSubsystem;
import SearchForSitesSubsystem.SearchForSitesSubsystem;

/**
 * Facade for all subsystem
 */
public class CovidBAndTSystemFacade {

    private LoginSubsystem loginSubsystem;
    private HomeBookingSubsystem homeBookingSubsystem;
    private OnsiteBookingSubsystem onsiteBookingSubsystem;
    private OnsiteTestingSubsystem onsiteTestingSubsystem;
    private SearchForSitesSubsystem searchForSitesSubsystem;

    public CovidBAndTSystemFacade() {
        loginSubsystem = new LoginSubsystem();
        homeBookingSubsystem = new HomeBookingSubsystem();
        onsiteBookingSubsystem = new OnsiteBookingSubsystem();
        onsiteTestingSubsystem = new OnsiteTestingSubsystem();
        searchForSitesSubsystem = new SearchForSitesSubsystem();
    }

    public LoginSubsystem getLoginSubsystem() {
        return loginSubsystem;
    }

    /**
     * Sample code
     */
    public void function(){
        System.out.println("I am Facade");
        getLoginSubsystem().function();
        System.out.println("----------");
    }

    //TODO: GUI - 用户交互界面




}

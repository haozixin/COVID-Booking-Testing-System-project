package webServiceAPI.searchForSitesSubsystem;

import org.junit.Test;
import searchForSitesSubsystem.SitesCollection;

import java.io.IOException;

import static org.junit.Assert.*;

public class TestingSiteTest {

    @Test
    public void getLocation() throws IOException, InterruptedException {
        SitesCollection testingSites = SitesCollection.getInstance();
        testingSites.update();

//        System.out.println(testingSites.getTestingSite(0).getLocation());


    }

}
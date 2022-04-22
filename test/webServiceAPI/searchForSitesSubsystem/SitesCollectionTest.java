package webServiceAPI.searchForSitesSubsystem;

import org.junit.Test;
import testingSite.SitesCollection;

import java.io.IOException;

public class SitesCollectionTest {

    @Test
    public void update() throws IOException, InterruptedException {
        SitesCollection.getInstance().update();
        SitesCollection.getInstance().printList();
    }
}
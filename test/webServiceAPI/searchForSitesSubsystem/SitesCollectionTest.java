package webServiceAPI.searchForSitesSubsystem;

import org.junit.Test;
import testingSites.SitesCollection;

import java.io.IOException;

public class SitesCollectionTest {

    @Test
    public void update() throws IOException, InterruptedException {
        SitesCollection.getInstance().update();
        SitesCollection.getInstance().printWholeList();
    }
}
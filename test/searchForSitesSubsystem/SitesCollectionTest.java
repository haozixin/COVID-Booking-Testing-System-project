package searchForSitesSubsystem;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class SitesCollectionTest {

    @Test
    public void update() throws IOException, InterruptedException {
        SitesCollection.getInstance().update();
        SitesCollection.getInstance().printList();
    }
}
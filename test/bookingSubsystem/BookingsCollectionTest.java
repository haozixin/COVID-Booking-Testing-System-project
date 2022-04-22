package bookingSubsystem;

import bookings.BookingsCollection;
import junit.framework.TestCase;

import java.io.IOException;

public class BookingsCollectionTest extends TestCase {

    public void testGetInstance() {
    }

    public void testUpdate() {
    }

    public void testGetStatusByPin() throws IOException, InterruptedException {
        BookingsCollection bc = BookingsCollection.getInstance();
        bc.update();
        System.out.println(bc.getStatusByPin("757831"));
    }
}
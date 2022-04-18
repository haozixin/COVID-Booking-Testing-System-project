import enums.Path;
import org.junit.Test;
import users.Customer;
import users.User;
import webServiceAPI.Services;
import webServiceAPI.WebServices;

import java.io.IOException;

import static org.junit.Assert.*;

public class BookingTest {

    @Test
    public void buildRequestBody() throws IOException, InterruptedException {
        WebServices service = new Services();
        Booking booking = new Booking("f3dc6047-1e2f-4c65-909f-37189cfd98df","7fbd25ee-5b64-4720-b1f6-4f6d4731260e","Testdata2","2010-01-01T12:00:00+01:00");
        String jsonString = booking.buildRequestBody();
        int responseNumber = service.postData(Path.BOOKING.getPath(), jsonString);
        System.out.println("In ServiceTest : " + responseNumber);
    }
}
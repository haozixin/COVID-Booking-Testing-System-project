package webServiceAPI;

import com.fasterxml.jackson.databind.node.ObjectNode;
import enums.Path;
import org.junit.Test;
import users.CurrentUser;
import users.Customer;

import java.io.IOException;

public class ServicesTest {

    @Test
    public void getAllData() throws IOException, InterruptedException {

        WebServices service = new Services();

        ObjectNode[] jsonNodes = service.getAllData(Path.SITE.getPath());

        for (ObjectNode node : jsonNodes) {
            System.out.println(node.toString());
        }
    }

    @Test
    public void getToken() {
    }

    @Test
    public void verifyToken() {
    }

    @Test
    public void postData() throws IOException, InterruptedException {
        WebServices service = new Services();
        CurrentUser user = new Customer();
        String jsonString = user.buildRequestBody();
        int responseNumber = service.postData(Path.USER.getPath(), jsonString);
        System.out.println("In ServiceTest : " + responseNumber);
    }
}
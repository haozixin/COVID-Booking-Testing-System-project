package webServiceAPI;

import com.fasterxml.jackson.databind.node.ObjectNode;
import enums.Path;
import org.junit.Test;

import java.io.IOException;

public class ServicesTest {

    @Test
    public void getAllData() throws IOException, InterruptedException {

        WebServices service = new Services();

        ObjectNode[] jsonNodes = service.getAllData(Path.SITE.getPath());

        for (ObjectNode node: jsonNodes) {
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
    public void signup() throws IOException, InterruptedException {
        WebServices service = new Services();
        String jsonString = "";
        int responseNumber = service.postData(Path.USER.getPath(),jsonString);
        System.out.println("In ServiceTest : "+responseNumber);
    }
}
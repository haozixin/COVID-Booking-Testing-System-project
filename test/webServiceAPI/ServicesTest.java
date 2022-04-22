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

//    @Test
//    public void postData() throws IOException, InterruptedException {
//        WebServices service = new Services();
//        User user = new Customer();
//        String jsonString = user.buildRequestBody();
//        int responseNumber = service.postData(Path.USER.getPath(), jsonString);
//        System.out.println("In ServiceTest : " + responseNumber);
//    }

    @Test
    public void putData() throws IOException, InterruptedException {
        WebServices service = new Services();
        String jsonNode = "{\n" +
                "  \"givenName\": \"putZixin2\",\n" +
                "  \"familyName\": \"string\",\n" +
                "  \"phoneNumber\": \"string\",\n" +
                "  \"isCustomer\": false,\n" +
                "  \"isReceptionist\": false,\n" +
                "  \"isHealthcareWorker\": false,\n" +
                "  \"additionalInfo\": {}\n" +
                "}";

        String id = "8678bb27-7582-49e8-a981-47b517201886";
        service.putData(Path.USER.getPath(), jsonNode,id);
    }

    @Test
    public void patchData() throws IOException, InterruptedException {
        WebServices service = new Services();
        String jsonNode = "{\n" +
//                "  \"phoneNumber\": \"string\",\n" +
                "  \"isHealthcareWorker\": true,\n" +
                "}";

        String id = "8678bb27-7582-49e8-a981-47b517201886";
        service.patchData(Path.USER.getPath(), jsonNode,id);
    }
}
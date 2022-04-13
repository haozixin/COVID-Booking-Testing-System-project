package webServiceAPI;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import enums.Path;
import enums.ResponseStatus;
import org.junit.Test;
import searchForSitesSubsystem.TestingSite;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.junit.Assert.*;

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


}
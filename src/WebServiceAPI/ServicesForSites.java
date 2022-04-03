package WebServiceAPI;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ServicesForSites extends WebServices{
    public static final String FAIL_MESSAGE = "Failed! Please check your api-key is correct!";
    private final String usersUrl = rootUrl + "/testing-site";

    @Override
    public String getTestingSites() throws IOException, InterruptedException{
        HttpRequest request = HttpRequest
                .newBuilder(URI.create(usersUrl))
                .setHeader("Authorization", myApiKey)
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() != 200) {
            return FAIL_MESSAGE;
        }
        else{
            // The GET /user endpoint returns a JSON array, so we can loop through the response as we could with a normal array/list.
            ObjectNode[] jsonNodes = new ObjectMapper().readValue(response.body(), ObjectNode[].class);

            for (ObjectNode node: jsonNodes) {
                System.out.println(node.toString());
//                System.out.println(node.get("id"));

            }

            System.out.println("----\n\n");
            return "yes";
        }
    }
}

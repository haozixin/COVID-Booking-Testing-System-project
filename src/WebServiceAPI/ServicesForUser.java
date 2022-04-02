package WebServiceAPI;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import enums.ResponseStatus;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * This class will implement Web Services about "user"
 */
public class ServicesForUser extends WebServices{
    public static final String SUCCESS_202 = "SUCCESS_202";

    private String usersUrl = rootUrl + "/user";



    // Haven't used
    @Override
    public void getAllUsers() throws IOException, InterruptedException {


        HttpRequest request = HttpRequest
                .newBuilder(URI.create(usersUrl))
                .setHeader("Authorization", myApiKey)
                .GET()
                .build();

        HttpResponse<String> response = null;

        response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println("\n----");
        System.out.println(request.uri());
        if (response != null) {
            System.out.println("Response code: " + response.statusCode());
        }
        if (response != null) {
            System.out.println("Full JSON response: " + response.body());
        }

    }

    /**
     * override the parents' method
     * @param userName
     * @param password
     * @return
     */
    @Override
    public String getToken(String userName, String password) throws IOException, InterruptedException {

        String jsonString = "{" +
                "\"userName\":\"" + userName + "\"," +
                "\"password\":\"" + password + "\"" +
                "}";

        // Note the POST() method being used here, and the request body is supplied to it.
        // A request body needs to be supplied to this endpoint, otherwise a 400 Bad Request error will be returned.
        String usersLoginUrl = usersUrl + "/login";
        client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(URI.create(usersLoginUrl + "?jwt=true")) // Return a JWT so we can use it in Part 5 later.
                .setHeader("Authorization", myApiKey)
                .header("Content-Type","application/json") // This header needs to be set when sending a JSON request body.
                .POST(HttpRequest.BodyPublishers.ofString(jsonString))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println("\n----");
        System.out.println(request.uri());
        System.out.println("Response code: " + response.statusCode());
        System.out.println("Full JSON response: " + response.body()); // The JWT token that has just been issued will be returned since we set ?jwt=true.
        System.out.println("----\n\n");

        ObjectNode jsonNode = new ObjectMapper().readValue(response.body(), ObjectNode.class);
        String token = jsonNode.get("jwt").textValue();




        if (response.statusCode()==200){
            return token;
        }
        else {
            return null;
        }

    }


    @Override
    public int verifyToken(String token) throws IOException, InterruptedException {

        String jsonString = "{\"jwt\":\"" + token + "\"}";

        // Note the POST() method being used here, and the request body is supplied to it.
        // A request body needs to be supplied to this endpoint, otherwise a 400 Bad Request error will be returned.
        String usersVerifyTokenUrl = usersUrl + "/verify-token";
        client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(URI.create(usersVerifyTokenUrl)) // Return a JWT so we can use it in Part 5 later.
                .setHeader("Authorization", myApiKey)
                .header("Content-Type","application/json") // This header needs to be set when sending a JSON request body.
                .POST(HttpRequest.BodyPublishers.ofString(jsonString))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println("\n----");
        System.out.println(request.uri());
        System.out.println("Response code: " + response.statusCode());
        System.out.println("Full JSON response: " + response.body()); // This endpoint does not return a JSON response upon success.
        System.out.println("----\n\n");

        return response.statusCode();
    }
}

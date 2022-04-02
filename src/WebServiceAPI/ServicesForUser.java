package WebServiceAPI;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * This class will implement Web Services about "user"
 */
public class ServicesForUser extends WebServices{

    private final String usersUrl = rootUrl + "/user";


    // Haven't used
    @Override
    public void getAllUsers() {

        HttpRequest request = HttpRequest
                .newBuilder(URI.create(usersUrl))
                .setHeader("Authorization", myApiKey)
                .GET()
                .build();

        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Part 2\n----");
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
    public String authentication(String userName, String password) {
        String jsonString = "{" +
                "\"userName\":\"" + userName + "\"," +
                "\"password\":\"" + password + "\"" +
                "}";
        return jsonString;
    }

}

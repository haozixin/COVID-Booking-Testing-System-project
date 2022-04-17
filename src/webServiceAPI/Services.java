package webServiceAPI;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import enums.Path;
import enums.ResponseStatus;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * This class will implement Web Services about "user"
 */
public class Services extends WebServices {


    /**
     * get all xx data(based on different parameter/path)
     * return ObjectNode[] jsonNodes that contains data
     *
     * @param path the path of the operation to be performed (e.g. /users/login), see enums.Path or documentation for more details
     */
    @Override
    public ObjectNode[] getAllData(String path) throws IOException, InterruptedException {

        String url = rootUrl + path;
        HttpRequest request = HttpRequest
                .newBuilder(URI.create(url))
                .setHeader("Authorization", myApiKey)
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() == ResponseStatus.CODE_200.getCode()) {
            System.out.println(ResponseStatus.matchCode(response.statusCode()));
            ObjectNode[] jsonNodes = new ObjectMapper().readValue(response.body(), ObjectNode[].class);
            return jsonNodes;
        } else {
            System.out.println(ResponseStatus.matchCode(response.statusCode()));
            return null;
        }
    }

    /**
     * override the parents' method
     *
     * @param userName
     * @param password
     * @return
     */
    @Override
    public String getToken(String userName, String password) throws IOException, InterruptedException {
        //TODO: 重构（解决System.out.println的问题）
        String url = rootUrl + Path.USER;

        String jsonString = "{" +
                "\"userName\":\"" + userName + "\"," +
                "\"password\":\"" + password + "\"" +
                "}";

        // Note the POST() method being used here, and the request body is supplied to it.
        // A request body needs to be supplied to this endpoint, otherwise a 400 Bad Request error will be returned.
        String usersLoginUrl = url + "/login";
        client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(URI.create(usersLoginUrl + "?jwt=true")) // Return a JWT so we can use it in Part 5 later.
                .setHeader("Authorization", myApiKey)
                .header("Content-Type", "application/json") // This header needs to be set when sending a JSON request body.
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


        if (response.statusCode() == ResponseStatus.CODE_200.getCode()) {
            return token;
        } else {
            return null;
        }

    }


    @Override
    public int verifyToken(String token) throws IOException, InterruptedException {

        //TODO: 重构（解决System.out.println的问题）
        String url = rootUrl + Path.USER;

        String jsonString = "{\"jwt\":\"" + token + "\"}";

        // Note the POST() method being used here, and the request body is supplied to it.
        // A request body needs to be supplied to this endpoint, otherwise a 400 Bad Request error will be returned.
        String usersVerifyTokenUrl = url + "/verify-token";
        client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(URI.create(usersVerifyTokenUrl)) // Return a JWT so we can use it in Part 5 later.
                .setHeader("Authorization", myApiKey)
                .header("Content-Type", "application/json") // This header needs to be set when sending a JSON request body.
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

    @Override
    public int postData(String path, String jsonString) throws IOException, InterruptedException {
        String url = rootUrl + path;

        // webservice call - post request
        HttpRequest request = HttpRequest.newBuilder(URI.create(url)) // Return a JWT so we can use it in Part 5 later.
                .setHeader("Authorization", myApiKey)
                .header("Content-Type", "application/json") // This header needs to be set when sending a JSON request body.
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
package webServiceAPI;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * WebServicesAdaptee
 */
public class WebServicesAdaptee {

    /**
     * This method is used to make a GET request to the API.
     * @param url
     * @param myApiKey
     * @param client
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
    public HttpResponse<String> getRequest(String url, String myApiKey, HttpClient client) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest
                .newBuilder(URI.create(url))
                .setHeader("Authorization", myApiKey)
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response;
    }

    /**
     * This method is used to make a POST request to the API.
     * @param url     The URL of the API.
     * @param myApiKey The API key.
     * @param jsonString
     * @param client
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
    public HttpResponse<String> postRequest(String url, String myApiKey, String jsonString, HttpClient client) throws IOException, InterruptedException {

        // Note the POST() method being used here, and the request body is supplied to it.
        // A request body needs to be supplied to this endpoint, otherwise a 400 Bad Request error will be returned.

        HttpRequest request = HttpRequest.newBuilder(URI.create(url)) // Return a JWT so we can use it in Part 5 later.
                .setHeader("Authorization", myApiKey)
                .header("Content-Type", "application/json") // This header needs to be set when sending a JSON request body.
                .POST(HttpRequest.BodyPublishers.ofString(jsonString))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        return response;
    }

    /**
     * This method is used to make a PUT request to the API.
     * @param url
     * @param myApiKey
     * @param jsonString
     * @param client
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
    public HttpResponse<String> putRequest(String url, String myApiKey, String jsonString, HttpClient client) throws IOException, InterruptedException {

        HttpRequest request = HttpRequest.newBuilder(URI.create(url)) // Return a JWT so we can use it in Part 5 later.
                .setHeader("Authorization", myApiKey)
                .header("Content-Type", "application/json") // This header needs to be set when sending a JSON request body.
                .PUT(HttpRequest.BodyPublishers.ofString(jsonString))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        return response;
    }

    public HttpResponse<String> patchRequest(String url, String myApiKey, String jsonString, HttpClient client) throws IOException, InterruptedException {

        HttpRequest request2 = HttpRequest.newBuilder(URI.create(url))
                .setHeader("Authorization", myApiKey)
                .header("Content-Type","application/json") // This header needs to be set when sending a JSON request body.
                .header("Accept","application/json")
                .method("PATCH",HttpRequest.BodyPublishers.ofString(jsonString))
                .build();
        HttpResponse<String> response = client.send(request2, HttpResponse.BodyHandlers.ofString());
        return response;
    }








}

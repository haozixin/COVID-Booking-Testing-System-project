package webServiceAPI;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import enums.Path;
import enums.ResponseStatus;
import utility.Utility;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * This class will implement Web Services about "user"
 */
public class ServicesAdapter implements WebServicesTarget {

    // Provide the root URL for the web service. All web service request URLs start with this root URL.
    private final String rootUrl;
    //myApiKey can **ever never** be shown in code and push to git
    private final String myApiKey;

    private HttpClient client;

    private WebServicesAdaptee webServicesAdaptee;

    public ServicesAdapter() {
        webServicesAdaptee = new WebServicesAdaptee();
        myApiKey = Utility.getMyConf("API_key");
        rootUrl = Utility.getMyConf("rootUrl");
        client = HttpClient.newHttpClient();
    }

    /**
     * get all xx data(based on different parameter/path)
     * return ObjectNode[] jsonNodes that contains data
     *
     * @param path the path of the operation to be performed (e.g. /users/login), see enums.Path or documentation for more details
     */
    @Override
    public ObjectNode[] getAllData(String path) throws IOException, InterruptedException {

        String url = rootUrl + path;

        HttpResponse<String> response = webServicesAdaptee.getRequest(url, myApiKey, client);

        boolean result = dealingResult(response, ResponseStatus.CODE_200.getCode());

        if (result) {
            return new ObjectMapper().readValue(response.body(), ObjectNode[].class);
        }
        return null;

    }


    // This request will succeed (assuming there was at least one user object returned in the array from Part 2.

    /**
     * fetch a particular resource by ID(based on different parameter/path)
     * return ObjectNode jsonNode that contains data
     *
     * @param path the path of the operation to be performed (e.g. /users/login), see enums.Path or documentation for more details
     * @param id   the id of that you are interested in
     */
    @Override
    public ObjectNode getSpecificData(String path, String id) throws IOException, InterruptedException {
        String url = rootUrl + path + "/" + id;

        HttpResponse<String> response = webServicesAdaptee.getRequest(url, myApiKey, client);


        boolean result = dealingResult(response, ResponseStatus.CODE_200.getCode());

        if (result) {
            return new ObjectMapper().readValue(response.body(), ObjectNode.class);
        }
        return null;

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

        String url = rootUrl + Path.USER_LOGIN.getPath() + "?jwt=true";

        String jsonString = "{" +
                "\"userName\":\"" + userName + "\"," +
                "\"password\":\"" + password + "\"" +
                "}";

        // Note the POST() method being used here, and the request body is supplied to it.
        // A request body needs to be supplied to this endpoint, otherwise a 400 Bad Request error will be returned.
        HttpResponse<String> response = webServicesAdaptee.postRequest(url, myApiKey, jsonString, client );

        ObjectNode jsonNode = new ObjectMapper().readValue(response.body(), ObjectNode.class);
        String token = jsonNode.get("jwt").textValue();

        boolean result = dealingResult(response, ResponseStatus.CODE_200.getCode());
        if (result) {
            return token;
        }
        return null;
    }


    @Override
    public boolean verifyToken(String token) throws IOException, InterruptedException {

        //TODO: 重构（解决System.out.println的问题）
        String url = rootUrl + Path.VERIFY_TOKEN.getPath();

        String jsonString = "{\"jwt\":\"" + token + "\"}";

        HttpResponse<String> response = webServicesAdaptee.postRequest(url, myApiKey, jsonString, client );

        return dealingResult(response, ResponseStatus.CODE_200.getCode());
    }


    @Override
    public boolean postData(String path, String jsonString) throws IOException, InterruptedException {
        String url = rootUrl + path;
        // webservice call - post request
        HttpResponse<String> response = webServicesAdaptee.postRequest(url, myApiKey, jsonString, client );
        return dealingResult(response, ResponseStatus.CODE_201.getCode());
    }


    @Override
    public boolean putData(String path, String jsonString, String id) throws IOException, InterruptedException {
        String url = rootUrl + path + "/" + id;
        // webservice call - post request
        HttpResponse<String> response = webServicesAdaptee.postRequest(url, myApiKey, jsonString, client );
        return dealingResult(response, ResponseStatus.CODE_200.getCode());
    }

    private boolean dealingResult(HttpResponse<String> response, int successCode) throws JsonProcessingException {
        if (response.statusCode() == successCode) {
            System.out.println(ResponseStatus.matchCode(response.statusCode()));
            return true;

        } else {
            System.out.println(ResponseStatus.matchCode(response.statusCode()));
            ObjectNode errorJsonNode = new ObjectMapper().readValue(response.body(), ObjectNode.class);
            Utility.resolveError(errorJsonNode);
            return false;
        }
    }


}
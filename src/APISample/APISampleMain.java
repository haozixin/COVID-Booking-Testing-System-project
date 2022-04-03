package APISample;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class APISampleMain {
    /*
    NOTE: In order to access the web service, you will need to include your API key in the Authorization header of all requests you make.
    Your personal API key can be obtained here: https://fit3077.com
   */
    private static final String myApiKey = "hzNMQKz7MBKmgNLG6tQFtBzddLGf6q";

    // Provide the root URL for the web service. All web service request URLs start with this root URL.
    private static final String rootUrl = "https://fit3077.com/api/v1";

    public static void main(String[] args) throws Exception {
        // To get a specific resource from the web service, extend the root URL by appending the resource type you are looking for.
        // For example: [root_url]/user will return a JSON array object containing all users.
        String usersUrl = rootUrl + "/user";

    /*
      Part 1 - Unauthenticated requests
     */

        // This request is unauthenticated (API key not attached), and will return an error.
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest
                .newBuilder(URI.create(usersUrl))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println("Part 1\n----");
        System.out.println(request.uri());
        System.out.println("Response code: " + response.statusCode()); // Status code of 4xx or 5xx indicates an error with the request or with the server, respectively.
        System.out.println("Full JSON response: " + response.body());
        System.out.println("----\n\n");


    /*
      Part 2 - Performing GET requests that return an array of objects.
     */

        // This request is authenticated (API key attached in the Authorization header), and will return the JSON array object containing all users.
        client = HttpClient.newHttpClient();
        request = HttpRequest
                .newBuilder(URI.create(usersUrl))
                .setHeader("Authorization", myApiKey)
                .GET()
                .build();

        response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println("Part 2\n----");
        System.out.println(request.uri());
        System.out.println("Response code: " + response.statusCode());
        System.out.println("Full JSON response: " + response.body());

        System.out.println();

        // Error checking for this sample code. You can check the status code of your request, as part of performing error handling in your assignment.
        if (response.statusCode() != 200) {
            throw new Exception("Please specify your API key in line 38 to continue using this sample code.");
        }

        // The GET /user endpoint returns a JSON array, so we can loop through the response as we could with a normal array/list.
        ObjectNode[] jsonNodes = new ObjectMapper().readValue(response.body(), ObjectNode[].class);

        for (ObjectNode node: jsonNodes) {
            System.out.println(node.toString());
        }

        System.out.println("----\n\n");

        // Get the first user object in the array and keep for future use in later parts of this sample code.
        ObjectNode userObjectForPart3 = jsonNodes[0];

    /*
      Part 3a - Performing an invalid GET request to fetch a particular resource by ID.
     */

        // To get a particular User resource, you will need to specify the user ID you are interested in. Simply extend the users_url by appending the user's id.
        String usersIdUrl = usersUrl + "/this-is-my-make-believe-id";

        // This request will fail as the user's ID is invalid/does not exist.
        client = HttpClient.newHttpClient();
        request = HttpRequest
                .newBuilder(URI.create(usersIdUrl))
                .setHeader("Authorization", myApiKey)
                .GET()
                .build();

        response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println("Part 3a\n----");
        System.out.println(request.uri());
        System.out.println("Response code: " + response.statusCode());
        System.out.println("Full JSON response: " + response.body());
        System.out.println("----\n\n");


    /*
      Part 3b - Performing a valid GET request to fetch a particular resource by ID
     */

        // This request will succeed (assuming there was at least one user object returned in the array from Part 2.
        usersIdUrl = usersUrl + "/" + userObjectForPart3.get("id").textValue();
        client = HttpClient.newHttpClient();
        request = HttpRequest
                .newBuilder(URI.create(usersIdUrl))
                .setHeader("Authorization", myApiKey)
                .GET()
                .build();

        response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println("Part 3b\n----");
        System.out.println(request.uri());
        System.out.println("Response code: " + response.statusCode());
        System.out.println("Full JSON response: " + response.body());
        System.out.println("----\n\n");


    /*
      Part 3c - Performing a valid GET request to fetch a particular resource by ID, with query parameters.
     */

        // To get more data for users (e.g. their bookings and COVID tests taken), you can add query params to the request.
        // You can directly append the query string to the end of the request URL - e.g. usersIdUrl + "?fields=bookings"
        // To get multiple fields, you can use: usersIdUrl + "?fields=bookings&fields=testsTaken"
        client = HttpClient.newHttpClient();
        request = HttpRequest
                .newBuilder(URI.create(usersIdUrl + "?fields=bookings"))
                .setHeader("Authorization", myApiKey)
                .GET()
                .build();

        response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println("Part 3c\n----");
        System.out.println(request.uri());
        System.out.println("Response code: " + response.statusCode());
        System.out.println("Full JSON response: " + response.body()); // Note that the object now has the bookings property returned.
        System.out.println("----\n\n");

    /*
      Part 4 - Authenticating a user's credentials using the POST /user/login endpoint
     */

        // Create the request body. The password for each of the sample user objects that have been created for you are the same as their respective usernames.
        String jsonString = "{" +
                "\"userName\":\"" + userObjectForPart3.get("userName").textValue() + "\"," +
                "\"password\":\"" + userObjectForPart3.get("userName").textValue() + "\"" +
                "}";

        // Note the POST() method being used here, and the request body is supplied to it.
        // A request body needs to be supplied to this endpoint, otherwise a 400 Bad Request error will be returned.
        String usersLoginUrl = usersUrl + "/login";
        client = HttpClient.newHttpClient();
        request = HttpRequest.newBuilder(URI.create(usersLoginUrl + "?jwt=true")) // Return a JWT so we can use it in Part 5 later.
                .setHeader("Authorization", myApiKey)
                .header("Content-Type","application/json") // This header needs to be set when sending a JSON request body.
                .POST(HttpRequest.BodyPublishers.ofString(jsonString))
                .build();

        response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println("Part 4\n----");
        System.out.println(request.uri());
        System.out.println("Response code: " + response.statusCode());
        System.out.println("Full JSON response: " + response.body()); // The JWT token that has just been issued will be returned since we set ?jwt=true.
        System.out.println("----\n\n");


    /*
      Part 5a - Verifying a JWT using the POST /user/verify-token endpoint
     */
        ObjectNode jsonNode = new ObjectMapper().readValue(response.body(), ObjectNode.class);

        jsonString = "{\"jwt\":\"" + jsonNode.get("jwt").textValue() + "\"}";

        // Note the POST() method being used here, and the request body is supplied to it.
        // A request body needs to be supplied to this endpoint, otherwise a 400 Bad Request error will be returned.
        String usersVerifyTokenUrl = usersUrl + "/verify-token";
        client = HttpClient.newHttpClient();
        request = HttpRequest.newBuilder(URI.create(usersVerifyTokenUrl)) // Return a JWT so we can use it in Part 5 later.
                .setHeader("Authorization", myApiKey)
                .header("Content-Type","application/json") // This header needs to be set when sending a JSON request body.
                .POST(HttpRequest.BodyPublishers.ofString(jsonString))
                .build();

        response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println("Part 5a\n----");
        System.out.println(request.uri());
        System.out.println("Response code: " + response.statusCode());
        System.out.println("Full JSON response: " + response.body()); // This endpoint does not return a JSON response upon success.
        System.out.println("----\n\n");


    /*
      Part 5b - Verifying a forged/tampered JWT using the POST /user/verify-token endpoint
     */
        jsonString = "{\"jwt\":\"" + jsonNode.get("jwt").textValue() + "tampered" + "\"}"; // Tamper with the JWT.

        // Note the POST() method being used here, and the request body is supplied to it.
        // A request body needs to be supplied to this endpoint, otherwise a 400 Bad Request error will be returned.
        // A JSON response will only be returned by the POST /user/verify-token endpoint if there were problems verifying the JWT.
        client = HttpClient.newHttpClient();
        request = HttpRequest.newBuilder(URI.create(usersVerifyTokenUrl))
                .setHeader("Authorization", myApiKey)
                .header("Content-Type","application/json") // This header needs to be set when sending a JSON request body.
                .POST(HttpRequest.BodyPublishers.ofString(jsonString))
                .build();

        response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println("Part 5b\n----");
        System.out.println(request.uri());
        System.out.println("Response code: " + response.statusCode());
        System.out.println("Full JSON response: " + response.body()); // Note that all 4xx and 5xx errors are guaranteed to return a JSON response.
        System.out.println("----\n\n");
    }
}


/*
{"id":"ecc52cc1-a3e4-4037-a80f-62d3799645f4","givenName":"Mary","familyName":"Brown","userName":"mbrown123","phoneNumber":"0410000000","isCustomer":true,"isReceptionist":false,"isHealthcareWorker":true,"additionalInfo":{}}
{"id":"b1e0f080-0a8d-4ab0-9c8c-39a607cd5bc9","givenName":"James","familyName":"Li","userName":"jamesli42","phoneNumber":"0410000001","isCustomer":true,"isReceptionist":false,"isHealthcareWorker":false,"additionalInfo":{}}
{"id":"bb495c2c-3e30-4e4a-80e7-3954c448d128","givenName":"Alina","familyName":"Gupta","userName":"lina_g","phoneNumber":"0410000002","isCustomer":true,"isReceptionist":false,"isHealthcareWorker":false,"additionalInfo":{}}
{"id":"984e7871-ed81-4f75-9524-3d1870788b1f","givenName":"Junwei","familyName":"Wang","userName":"iamthewei","phoneNumber":"0410000003","isCustomer":false,"isReceptionist":true,"isHealthcareWorker":false,"additionalInfo":{}}
{"id":"a753826d-3c66-4f89-8136-9af896b5bfd9","givenName":"Jerry","familyName":"Mander","userName":"jrymdr","phoneNumber":"0410000004","isCustomer":true,"isReceptionist":true,"isHealthcareWorker":true,"additionalInfo":{}}
{"id":"d1580eb7-5ddb-4037-a4e6-d9b94fe493dd","givenName":"Ahmed","familyName":"Asif","userName":"aasif","phoneNumber":"0410000005","isCustomer":true,"isReceptionist":false,"isHealthcareWorker":false,"additionalInfo":{}}
{"id":"2919e042-d7ec-468a-9f58-30c5250d411b","givenName":"Kevin","familyName":"Kim","userName":"kevink","phoneNumber":"0410000006","isCustomer":false,"isReceptionist":true,"isHealthcareWorker":false,"additionalInfo":{}}
{"id":"3e541287-2ea2-4dad-b729-761d8f36059f","givenName":"Neil","familyName":"West","userName":"neilly","phoneNumber":"0410000007","isCustomer":true,"isReceptionist":false,"isHealthcareWorker":false,"additionalInfo":{}}
{"id":"9e3d2d23-61ee-460c-9b5d-d9882f0acb9e","givenName":"Kerri","familyName":"James","userName":"simplyawesome","phoneNumber":"0410000008","isCustomer":false,"isReceptionist":true,"isHealthcareWorker":false,"additionalInfo":{}}
{"id":"03163bcb-9825-47dc-8839-dfa7d264430f","givenName":"Nisha","familyName":"Patel","userName":"nishp94","phoneNumber":"041000009","isCustomer":false,"isReceptionist":true,"isHealthcareWorker":false,"additionalInfo":{}}
{"id":"70e136be-40ec-4e4b-8682-ac457f43a3cf","givenName":"Irene","familyName":"Young","userName":"iamyoung99","phoneNumber":"0410000010","isCustomer":true,"isReceptionist":false,"isHealthcareWorker":false,"additionalInfo":{}}
{"id":"14f4d87f-61e3-44e6-a0eb-292ec5546faf","givenName":"Greta","familyName":"Gutierrez","userName":"gretag13","phoneNumber":"0410000012","isCustomer":true,"isReceptionist":true,"isHealthcareWorker":false,"additionalInfo":{}}
{"id":"d90f9f94-7603-4231-805c-eb62158ad3c6","givenName":"Benjamin","familyName":"Ye","userName":"bigben","phoneNumber":"0410000013","isCustomer":true,"isReceptionist":true,"isHealthcareWorker":false,"additionalInfo":{}}
{"id":"1ed84243-50ac-437e-872e-39dbce04c5a4","givenName":"Angie","familyName":"Yoon","userName":"ayyy100","phoneNumber":"0410000014","isCustomer":true,"isReceptionist":false,"isHealthcareWorker":false,"additionalInfo":{}}
{"id":"2f8179cb-8325-4760-98ef-5b3f781ad596","givenName":"Ronald","familyName":"Low","userName":"ronlow","phoneNumber":"0410000015","isCustomer":false,"isReceptionist":true,"isHealthcareWorker":false,"additionalInfo":{}}
{"id":"4ad8f1ed-4883-4c44-a9ab-a50bdee96ff9","givenName":"Dmitri","familyName":"Antonovich","userName":"danto87","phoneNumber":"0410000016","isCustomer":false,"isReceptionist":true,"isHealthcareWorker":false,"additionalInfo":{}}
{"id":"f5449b1f-6f55-408a-88ee-194958f52219","givenName":"Daniella","familyName":"Brun","userName":"brunella","phoneNumber":"0410000017","isCustomer":true,"isReceptionist":true,"isHealthcareWorker":false,"additionalInfo":{}}
{"id":"9bf9d775-8c70-4b26-ad1c-4120c2abf446","givenName":"Fred","familyName":"Mercure","userName":"freddiem","phoneNumber":"0410000018","isCustomer":false,"isReceptionist":true,"isHealthcareWorker":true,"additionalInfo":{}}
{"id":"8678bb27-7582-49e8-a981-47b517201886","givenName":"zixin","familyName":"string","userName":"string","phoneNumber":"string","isCustomer":true,"isReceptionist":false,"isHealthcareWorker":true,"additionalInfo":{}}
----

{"id":"7fbd25ee-5b64-4720-b1f6-4f6d4731260e","name":"Monash University - Clayton","description":"RAT and PCR testing available. COVID testing available Monday to Friday by appointment. Pre-register online https://testtracker.covid19.dhhs.vic.gov.au/citizen-prefill; Then call 03 5871 0777 (option 6) to make your appointment time.","websiteUrl":null,"phoneNumber":"0358710777","address":{"latitude":-37,"longitude":145,"unitNumber":"133","street":"Wellington Road","street2":null,"suburb":"Clayton","state":"VIC","postcode":"3168","additionalInfo":{}},"createdAt":"2022-03-17T00:14:12.678Z","updatedAt":"2022-03-17T00:14:12.678Z","additionalInfo":{}}
"7fbd25ee-5b64-4720-b1f6-4f6d4731260e"
START_OBJECT
{"id":"ccad0b5b-0786-42d2-802d-3497c5eda14e","name":"4Cyte Pathology - Mount Waverley Drive-Through","description":null,"websiteUrl":"https://www.4cyte.com.au/OurLocations.php","phoneNumber":"0466725058","address":{"latitude":-37,"longitude":145,"unitNumber":"533","street":"Blackburn Road","street2":null,"suburb":"Mount Waverley","state":"VIC","postcode":"3149","additionalInfo":{}},"createdAt":"2022-03-17T00:14:12.678Z","updatedAt":"2022-03-17T00:14:12.678Z","additionalInfo":{}}
"ccad0b5b-0786-42d2-802d-3497c5eda14e"
 */
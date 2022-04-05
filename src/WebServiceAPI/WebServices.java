package WebServiceAPI;

import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.IOException;
import java.net.http.HttpClient;

/**
 * Following the Default Adapter pattern,
 * this class is a kind of default adapter class, the subclasses of the WebServices(son class) will implement the concrete stuff
 * IWebServices is a kind of Adaptee
 * All methods here will be empty method
 */
public abstract class WebServices{
    //myApiKey can **ever never** be shown in code and push to git
    protected static String myApiKey;

    // Provide the root URL for the web service. All web service request URLs start with this root URL.
    protected static final String rootUrl = "https://fit3077.com/api/v1";

    protected static HttpClient client = HttpClient.newHttpClient();

    public static void setKey(String key){
        myApiKey = key;
    }




    public String getToken(String userName, String password) throws IOException, InterruptedException {return null;}



    public int verifyToken(String token) throws IOException, InterruptedException {
        return 0;
    }



    // like database basic function
    public ObjectNode[] getAllData(String path) throws IOException, InterruptedException {return null;}




}

package WebServiceAPI;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * Following the Default Adapter pattern,
 * this class is a kind of default adapter class, the subclasses of the WebServices(son class) will implement the concrete stuff
 * IWebServices is a kind of Adaptee
 * All methods here will be empty method
 */
public abstract class WebServices implements IWebServices {
    //myApiKey can **ever never** be shown in code and push to git
    protected static String myApiKey;

    // Provide the root URL for the web service. All web service request URLs start with this root URL.
    protected static final String rootUrl = "https://fit3077.com/api/v1";

    protected static HttpClient client = HttpClient.newHttpClient();

    public static void setKey(String key){
        myApiKey = key;
    }

    /**
     * will be implemented on a concrete class
     */
    @Override
    public String authentication(String userName, String password){return null;}

    /**
     * will be implemented on a concrete class
     */
    @Override
    public void getAllUsers() {}

    /**
     * will be implemented on a concrete class
     */
    @Override
    public void otherInterface() {
    }
}

package webServiceAPI;

import com.fasterxml.jackson.databind.node.ObjectNode;
import enums.Path;
import utility.Utility;

import java.io.IOException;
import java.net.http.HttpClient;

/**
 * Following the Default Adapter pattern,
 * this class is a kind of default adapter class, the subclasses of the WebServices(son class) will implement the concrete stuff
 * IWebServices is a kind of Adaptee
 * All methods here will be empty method
 */
public abstract class WebServices{
    //TODO: 如果以后没有更多的服务需要子类继承WebServices， 将这个类改成接口
    //myApiKey can **ever never** be shown in code and push to git
    protected static String myApiKey = Utility.getMyConf("API_key").toString();

    // Provide the root URL for the web service. All web service request URLs start with this root URL.
    protected static final String rootUrl = Utility.getMyConf("rootUrl");

    protected static HttpClient client = HttpClient.newHttpClient();

    public String getToken(String userName, String password) throws IOException, InterruptedException {return null;}

    public int verifyToken(String token) throws IOException, InterruptedException {
        return 0;
    }

    // like database basic function
    public ObjectNode[] getAllData(String path) throws IOException, InterruptedException {return null;}

    //add Data to WebServer
    public ObjectNode addData(String path, ObjectNode data) throws IOException, InterruptedException {return null;}

    /**
     *
     * @param path URL path
     * @param jsonString a correct json string that can be parsed by Jackson and you want to add to the server
     * @return response state code
     * @throws IOException
     * @throws InterruptedException
     */
    public abstract int postData(String path, String jsonString) throws IOException, InterruptedException;

    public abstract int putData(String path, String jsonString) throws IOException, InterruptedException;

    public abstract int deleteData(String path, String jsonString, String id) throws IOException, InterruptedException;

    //通过找到booking --》 再返回status


}

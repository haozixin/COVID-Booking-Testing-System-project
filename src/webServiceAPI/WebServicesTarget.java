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
public interface WebServicesTarget {


    String getToken(String userName, String password) throws IOException, InterruptedException;

    boolean verifyToken(String token) throws IOException, InterruptedException;

    // like database basic function
    ObjectNode[] getAllData(String path) throws IOException, InterruptedException;



    /**
     *
     * @param path URL path
     * @param jsonString a correct json string that can be parsed by Jackson and you want to add to the server
     * @return response state code
     * @throws IOException
     * @throws InterruptedException
     */
    boolean postData(String path, String jsonString) throws IOException, InterruptedException;

    /**
     *  do same things with put or patch web request
     * @param path sub URL path
     * @param jsonString schema of the data
     * @param id the id of the data
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
    boolean putData(String path, String jsonString, String id) throws IOException, InterruptedException;

    // cannot implement - java don't support PATCH method
//    boolean patchData(String path, String jsonString, String id) throws IOException, InterruptedException;

    ObjectNode getSpecificData(String path, String id) throws IOException, InterruptedException;
}

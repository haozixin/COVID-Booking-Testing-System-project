package webServiceAPI;

import com.fasterxml.jackson.databind.node.ObjectNode;
import enums.Path;
import enums.Query;
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


    /**
     * like database basic function
     * get all xx data(based on different parameter/path)
     * return ObjectNode[] jsonNodes that contains data
     *
     * if the query is null - it will return all data
     * @param path the path of the operation to be performed (e.g. /users/login), see enums.Path or documentation for more details
     * @param query the query of the operation to be performed (e.g. ?id=1), see enums.Query or documentation for more details
     */
    ObjectNode[] getAllData(String path, String query) throws IOException, InterruptedException;



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

    /**
     *
     * @param path
     * @param id
     * @param query
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
    ObjectNode getSpecificData(String path, String id, String query) throws IOException, InterruptedException;
}

package webServiceAPI;

import com.fasterxml.jackson.databind.node.ObjectNode;
import enums.Path;
import enums.Query;
import utility.*;

import java.io.IOException;
import java.net.http.HttpClient;

/**
 * Following the Default Adapter pattern,
 * this class is a kind of default adapter class, the subclasses of the WebServices(son class) will implement the concrete stuff
 * IWebServices is a kind of Adaptee
 * All methods here will be empty method
 */
public interface WebServicesTarget {


    /**
     *  Get Token from the server
     * @param userName  user name
     * @param password  password
     * @return token
     * @throws IOException
     * @throws InterruptedException
     */
    String getToken(String userName, String password) throws IOException, InterruptedException;

    /**
     *  Verify the token
     * @param token the token that you want to verify
     * @return boolean value: true-success, false-failure
     * @throws IOException
     * @throws InterruptedException
     */
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
     * like database basic function
     * post the data to web service and update the database
     * @param path URL path
     * @param jsonString a correct json string that can be parsed by Jackson and you want to add to the server
     * @return boolean value: true-success, false-failure
     * @throws IOException
     * @throws InterruptedException
     */
    ObjectNode postData(String path, String jsonString) throws IOException, InterruptedException;

    /**
     * do same things with put or patch web request
     * @param path sub URL path
     * @param jsonString schema of the data
     * @param id the id of the data
     * @return boolean value: true-success, false-failure
     * @throws IOException
     * @throws InterruptedException
     */
    boolean putData(String path, String jsonString, String id) throws IOException, InterruptedException;

    // cannot implement - java don't support PATCH method
//    boolean patchData(String path, String jsonString, String id) throws IOException, InterruptedException;

    /**
     * get the specific data according to specific id
     * @param path URL path
     * @param id the id of the data
     * @param query the query of the operation to be performed (e.g. ?id=1), see enums.Query or documentation for more details
     * @return objectNode contains the specific data
     * @throws IOException
     * @throws InterruptedException
     */
    ObjectNode getSpecificData(String path, String id, String query) throws IOException, InterruptedException;

    /**
     *  patch the specific data according to specific id and specific data in the correct path
     * @param path URL path
     * @param jsonString    schema of the specific data (e.g. "{\"isReceptionist\": true}")
     * @param id the id of the object/entity
     * @return boolean value: true-success, false-failure
     * @throws IOException
     * @throws InterruptedException
     */
    boolean patchData(String path, String jsonString, String id) throws IOException, InterruptedException;
}

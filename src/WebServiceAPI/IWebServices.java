package WebServiceAPI;

import java.io.IOException;

/**
 * This interface defines all possible operations that we need to used
 */
public interface IWebServices {

    /**
     *  Performing GET requests that return an array of users(customers/receptionist/healthcareWorker/patients).
     *  Return the JSON array object containing all users
     */
    void getAllUsers() throws IOException, InterruptedException;

    /**
     * Authenticating a user's credentials using the POST /user/login endpoint
     * @param userName provide user name
     * @param password provide password
     * @return A string Token
     */
    String authentication(String userName, String password);

    //Sample noisy interface
    void otherInterface();

}

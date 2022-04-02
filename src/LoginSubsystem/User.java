package LoginSubsystem;

/**
 * User agency for users that need to do login
 * The class is used to store information from what we get using web service API and from actual users
 * and do the following steps of login.
 */
public class User {

    // other information will be added when we need it
    private String userName;
    private String token;

    /**
     * Constructor
     * @param userName String Data Type - userName used for login
     * @param token String Data Type - password used for login
     */
    public User(String userName, String token) {
        this.userName = userName;
        this.token = token;
    }



}

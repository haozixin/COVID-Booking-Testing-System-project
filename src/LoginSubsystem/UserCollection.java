package LoginSubsystem;

import java.util.ArrayList;

/**
 * User agency for users that need to do login
 * The class is used to store information from what we get using web service API and from actual users
 * and do the following steps of login.
 */
public class UserCollection {
    // Token contains' all information
    // use HashMap or List???
    private ArrayList<String> userArrayList;


    /**
     * Constructor
     */
    public UserCollection() {
        this.userArrayList = new ArrayList<>();
    }

    private void addUser(String token){
        userArrayList.add(token);
    }



}

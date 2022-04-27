package users;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import engine.DataCollection;
import engine.DataSubscriber;
import engine.Entity;
import enums.Path;
import testingSites.TestingSite;
import webServiceAPI.ServicesAdapter;
import webServiceAPI.WebServicesTarget;

import java.io.IOException;
import java.util.ArrayList;

/**
 * User collection class.
 * It is a collection of users - used to store all users in the system pulled from web server.
 */
public class UsersCollection implements DataSubscriber, DataCollection {
    private final ArrayList<User> users;
    private static UsersCollection instance = null;

    /**
     * Private Constructor for the singleton pattern
     */
    private UsersCollection() {
        users = new ArrayList<>();

    }

    /**
     * Get the instance of the user collection
     * @return the instance of the user collection
     */
    public static UsersCollection getInstance() {
        if (instance == null) {
            instance = new UsersCollection();
        }
        return instance;
    }

    /**
     * Update the jsonNodes array with the new data from the web service, will called by data publisher
     *
     * @return true if the update was successful, false otherwise
     * @throws IOException
     * @throws InterruptedException
     */
    @Override
    public void update() throws IOException, InterruptedException {
        WebServicesTarget ws = new ServicesAdapter();
        ObjectNode[] objectNode = ws.getAllData(Path.USER.getPath(), null);
        // remove all the testing sites(old data)
        clearUsers();
        // add the new testing sites
        for (ObjectNode node : objectNode) {
            User user = new User(node);
            addUser(user);
        }
    }

    /**
     * Clear the users from the user collection
     * For update date (we need to clean up the container before the update)
     */
    private void clearUsers() {
        users.clear();
    }

    /**
     * Add a user to the user collection
     * @param user the user to add
     */
    private void addUser(User user) {
        users.add(user);
    }

    /**
     * Get the user by the user name
     * @param userName the user name
     * @return A User object
     */
    @Override
    public Entity findEntity(String userName) {
        for (User user : users) {
            if (user.getUserName().equals(userName)) {
                return user;
            }
        }
        return null;
    }


    @Override
    public JsonNode searchForEntity(String userName, String query) throws IOException, InterruptedException {
        for (User user : users) {
            if (user.getUserName().equals(userName)) {
                String userId = user.getEntityId();
                WebServicesTarget ws = new ServicesAdapter();
                ObjectNode data = ws.getSpecificData(Path.USER.getPath(), userId, query);
                JsonNode list =  data.get(User.BOOKINGS);
                return list;
            }
        }
        return null;
    }

    @Override
    public void printWholeList() {
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Override
    public ArrayList<TestingSite> filterByOnFactor(String field, String value) {
        // haven't used this yet
        return null;
    }


}

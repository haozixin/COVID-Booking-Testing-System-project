package users;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import engine.DataSubscriber;
import enums.Path;
import enums.Query;
import webServiceAPI.ServicesAdapter;
import webServiceAPI.WebServicesTarget;

import java.io.IOException;
import java.util.ArrayList;


public class UserCollection implements DataSubscriber {
    private ArrayList<User> users;
    private static UserCollection instance = null;

    private UserCollection() {
        users = new ArrayList<>();
    }

    public static UserCollection getInstance() {
        if (instance == null) {
            instance = new UserCollection();
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
        clearTestingSites();
        // add the new testing sites
        for (ObjectNode node : objectNode) {
            User user = new User(node);
            addUser(user);
        }
    }

    private void clearTestingSites() {
        users.clear();
    }

    private void addUser(User user) {
        users.add(user);
    }


    public User findUser(String userName) {
        for (User user : users) {
            if (user.getUserName().equals(userName)) {
                return user;
            }
        }
        return null;
    }

    /**
     *
     * @param userName
     */
    public JsonNode searchForBooking(String userName) throws IOException, InterruptedException {

        for (User user : users) {
            if (user.getUserName().equals(userName)) {
                String userId = user.getUserId();
                WebServicesTarget ws = new ServicesAdapter();
                ObjectNode data = ws.getSpecificData(Path.USER.getPath(), userId, Query.BOOKINGS_IN_USER_OR_SITE.getQuery());
                JsonNode list =  data.get(User.BOOKINGS);
                return list;
            }
        }
        return null;
    }


}

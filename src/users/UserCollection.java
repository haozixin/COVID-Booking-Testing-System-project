package users;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import engine.DataCollection;
import engine.DataSubscriber;
import engine.Entity;
import enums.Path;
import enums.Query;
import testingSites.TestingSite;
import webServiceAPI.ServicesAdapter;
import webServiceAPI.WebServicesTarget;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;


public class UserCollection implements DataSubscriber, DataCollection {
    private final ArrayList<User> users;
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
    public void printList() {
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

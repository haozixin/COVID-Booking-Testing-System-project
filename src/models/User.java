package models;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import enums.Path;
import enums.Query;
import enums.UserRoles;
import utility.Utility;

import java.io.IOException;
import java.util.ArrayList;


public class User extends Model{

    public static final String ID_FIELD = "id";
    public static final String BOOKINGS_FIELD = "bookings";
    public static final String PHONE_NUMBER_FIELD = "phoneNumber";

    public static final String PASSWORD_FIELD = "password";

    public static final String USER_NAME_FIELD = "userName";

    public static final String FAMILY_NAME_FIELD = "familyName";

    public static final String GIVEN_NAME_FIELD = "givenName";

    /**
     * Constructor - get data and create a new entity through actor's input
     */
    public User() {

    }

    public boolean updateToServer() {
        String jsonNode = entityInfo.toString();
        boolean isUpdated = false;
        try {
            webServicesTarget.postData(Path.SIGN_UP.getPath(), jsonNode);
            isUpdated = true;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return isUpdated;
    }

    public String display(){
        return Utility.formatMessage(entityInfo);
    }


    public void setSchema(String phoneNumber, String password, String userName, String familyName, String givenName) {
        entityInfo.put(GIVEN_NAME_FIELD, givenName);
        entityInfo.put(FAMILY_NAME_FIELD, familyName);
        entityInfo.put(USER_NAME_FIELD, userName);
        entityInfo.put(PASSWORD_FIELD, password);
        entityInfo.put(PHONE_NUMBER_FIELD, phoneNumber);
        // we assume that the user is a customer by default
        entityInfo.put(UserRoles.IS_CUSTOMER_FIELD.getName(), true);
    }

    public void findBookingsByUserName(String userName) throws IOException, InterruptedException {

        ObjectNode[] users = webServicesTarget.getAllData(Path.USER.getPath(), Query.BOOKINGS_IN_USER_OR_SITE.getQuery());
        for (ObjectNode user : users) {
            if (user.get(USER_NAME_FIELD).asText().equals(userName)) {
                this.entityInfo = user;
            }
        }
    }

    public String getBookings(){
        return Utility.displayJsonList(entityInfo.get(BOOKINGS_FIELD));
    }


}

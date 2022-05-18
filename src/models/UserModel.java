package models;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import enums.Path;
import enums.Query;
import enums.UserRoles;
import utility.Utility;

import java.io.IOException;
import java.util.ArrayList;


public class UserModel extends EntityModel {

    public static final String ID_FIELD = "id";
    public static final String BOOKINGS_FIELD = "bookings";
    public static final String PHONE_NUMBER_FIELD = "phoneNumber";

    public static final String PASSWORD_FIELD = "password";

    public static final String USER_NAME_FIELD = "userName";

    public static final String FAMILY_NAME_FIELD = "familyName";

    public static final String GIVEN_NAME_FIELD = "givenName";

    /**
     * Constructor - default constructor
     */
    public UserModel() {

    }

    /**
     * Constructor2 - get data and create a new entity through parameters
     *
     * @param data
     */
    public UserModel(ObjectNode data) {
        updateModel(data);
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

    public String display() {
        return Utility.formatMessage(entityInfo, "");
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

    /**
     * get the specified User information(including bookings) from the server by the given userName
     *
     * @param userName the userName of the user(patients/...)
     * @return the user information
     * @throws IOException
     * @throws InterruptedException
     */
    public boolean findBookingsByUserName(String userName) throws IOException, InterruptedException {
        boolean isFound = false;
        ObjectNode[] users = webServicesTarget.getAllData(Path.USER.getPath(), Query.BOOKINGS_IN_USER_OR_SITE.getQuery());
        responseMessage = webServicesTarget.getResponseMessage();

        for (ObjectNode user : users) {
            if (user.get(USER_NAME_FIELD).asText().equals(userName)) {
                this.entityInfo = user;
                isFound = true;
            }
        }
        return isFound;
    }

    public String getId() {
        try {
            return entityInfo.get(ID_FIELD).asText();
        } catch (Exception e) {
            return null;
        }
    }

    public String getBookings() {
        return Utility.displayJsonList(entityInfo.get(BOOKINGS_FIELD));
    }

    public String getPhoneNumber() {
        try {
            return entityInfo.get(PHONE_NUMBER_FIELD).asText();
        } catch (NullPointerException e) {
            return "";
        }
    }

    public String getGivenName() {
        try {
            return entityInfo.get(GIVEN_NAME_FIELD).asText();
        } catch (NullPointerException e) {
            return "";
        }
    }


    public String getFamilyName() {
        try {
            return entityInfo.get(FAMILY_NAME_FIELD).asText();
        } catch (NullPointerException e) {
            return "";
        }
    }


    public String getUserName() {
        try {
            return entityInfo.get(USER_NAME_FIELD).asText();
        } catch (NullPointerException e) {
            return "";
        }
    }

    public ArrayList<OnsiteBookingModel> getOnSiteBookings() {
        ArrayList<OnsiteBookingModel> onSiteBookings = new ArrayList<>();

        try {
            ObjectNode data = webServicesTarget.getSpecificData(Path.USER.getPath(), getId(), Query.COVIDTESTS_IN_USER_OR_SITE.getQuery());
            updateModel(data);
            responseMessage = webServicesTarget.getResponseMessage();
            for (JsonNode booking : data.get(BOOKINGS_FIELD)) {
                String bookingType = booking.findValue(OnsiteBookingModel.BOOKING_TYPE_FIELD).asText();
                if (bookingType.equals(OnsiteBookingModel.ONSITE)) {
                    // map JsonNode to ObjectNode
                    ObjectNode bookingObject = (ObjectNode) booking;

                    onSiteBookings.add(new OnsiteBookingModel(bookingObject));
                }
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return onSiteBookings;
    }

}

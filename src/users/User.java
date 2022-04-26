package users;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import engine.Entity;
import enums.UserRoles;
import utility.Utility;

import java.util.HashMap;

/**
 * User class is used to deal with user data
 */
public class User extends Entity {

    public static String className = "User";

    public static final String PHONE_NUMBER_FIELD = "phoneNumber";

    public static final String PASSWORD_FIELD = "password";

    public static final String USER_NAME_FIELD = "userName";

    public static final String FAMILY_NAME_FIELD = "familyName";

    public static final String GIVEN_NAME_FIELD = "givenName";

    public static final String BOOKINGS = "bookings";

    // when there is more roles, we only add one enums in UserRoles.java
    // stores isCustomer, isHealthcareProvider, ...
    public static HashMap<Character, String> keyToRoleMap;

    /**
     * Constructor 1 - get data through parameters (from web server)
     *
     * @param entityInfo
     */
    public User(ObjectNode entityInfo) {
        super(entityInfo);
    }

    static {
        keyToRoleMap = new HashMap<Character, String>();
        int counter = 1;
        // if there is more role in the system, we just need add more roles here
        for (String role : UserRoles.getAllRoles()) {
            keyToRoleMap.put((char) counter, role);
            counter++;
        }
    }


    /**
     * Constructor 2 - get data and create a new entity through actor's input
     */
    private User() {
        // initial attributes Template(Schema)
        initialSchema();
        // update other data from user's input
        addValueToSchema();
    }

    /**
     * Only way to create a new user - Serialization the entity(from Object to String)
     * @return
     */
    public static String createNewEntity() {
        User user = new User();
        return user.getEntityInfo();
    }

    @Override
    public void display() {
        System.out.println(Utility.formatMessage(className, entityInfo));
    }


    /**
     * initial attributes Template(Schema)
     * uesd by constructor 2
     * is for post or put request (make a new entity)
     */
    @Override
    protected void initialSchema() {
        entityInfo = new ObjectMapper().createObjectNode();
        entityInfo.put(GIVEN_NAME_FIELD, "String");
        entityInfo.put(FAMILY_NAME_FIELD, "String");
        entityInfo.put(USER_NAME_FIELD, "String");
        entityInfo.put(PASSWORD_FIELD, "String");
        entityInfo.put(PHONE_NUMBER_FIELD, "String");
        // we assume that the user is a customer by default
        entityInfo.put(UserRoles.IS_CUSTOMER_FIELD.getName(), true);
        entityInfo.put(UserRoles.IS_ADMIN_FIELD.getName(), false);
        entityInfo.put(UserRoles.IS_HEALTHCARE_WORKER_FIELD.getName(), false);
    }

    /**
     * Get userName field value
     * @return  String userName
     */
    public String getUserName() {
        try {
            return entityInfo.get(USER_NAME_FIELD).asText();
        } catch (NullPointerException e) {
            return null;
        }

    }


}

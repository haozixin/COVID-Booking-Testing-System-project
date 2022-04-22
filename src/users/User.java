package users;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

/**
 *  User class is used to deal with user data
 */
public class User {
    private ObjectNode currentUserInfo;

    public static final String IS_CUSTOMER_FIELD = "isCustomer";

    public static final String IS_ADMIN_FIELD = "isReceptionist";

    public static final String IS_HEALTHCARE_WORKER_FIELD = "isHealthcareWorker";

    public static final String PHONE_NUMBER_FIELD = "phoneNumber";

    public static final String PASSWORD_FIELD = "password";

    public static final String USER_NAME_FIELD = "userName";

    public static final String FAMILY_NAME_FIELD = "familyName";

    public static final String GIVEN_NAME_FIELD = "givenName";

    HashMap<Character, String> keyToRoleMap = new HashMap<Character, String>();

    /**
     * Constructor 1 - get data through parameters (from web server)
     * @param currentUserInfo
     */
    public User(ObjectNode currentUserInfo) {
        this.currentUserInfo = currentUserInfo;
    }

    /**
     * used by user who is doing signup
     * (creating new entity by actor will be done by this method(collect user's input). It can avoid errors because of the missing required attributes.
     * and we don't need to change every outside places if there are more or less fields required on Web side)
     * @param
     * @return return a JSON String converted from ObjectNode, which is the current user info that is going to be saved in the system
     */
    public String buildRequestBody() {
        // 1. the role of user will be set to true

        // 2. get user's input
        System.out.println(keyToRoleMap);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please choose your role(please input the sequence number): ");
        Character choice = scanner.next().charAt(0);
        while (!keyToRoleMap.containsKey(choice)) {
            System.out.println("please enter correct number. ");
        }
        currentUserInfo.put(keyToRoleMap.get(choice), true);





        // 3. update other data from user's input
        for (Iterator<String> it = currentUserInfo.fieldNames(); it.hasNext(); ) {
            String key = it.next();
            // the value of the key is the type of String
            if (currentUserInfo.get(key).isTextual()) {
                System.out.print(key + " : ");
                Scanner s = new Scanner(System.in);
                String value = s.next();
                currentUserInfo.put(key, value);
            }
        }


        return currentUserInfo.toString();
    }

    /**
     * Constructor 2 - get data and create a new entity through actor's input
     */
    public User() {
        // if there is more role in the system, we just need add more roles here
        keyToRoleMap.put('1', IS_ADMIN_FIELD);
        keyToRoleMap.put('2', IS_CUSTOMER_FIELD);
        keyToRoleMap.put('3', IS_HEALTHCARE_WORKER_FIELD);

        // initial attributes Template(Schema)
        initialSchema();
    }


    /**
     * override the method to make IS_CUstomer_FIELD true/ IS_ADMIN_FIELD true/ IS_HEALTHCARE_WORKER_FIELD true
     */
    protected void initialSchema(){
        currentUserInfo = new ObjectMapper().createObjectNode();
        currentUserInfo.put(GIVEN_NAME_FIELD, "String");
        currentUserInfo.put(FAMILY_NAME_FIELD, "String");
        currentUserInfo.put(USER_NAME_FIELD, "String");
        currentUserInfo.put(PASSWORD_FIELD, "String");
        currentUserInfo.put(PHONE_NUMBER_FIELD, "String");
        // we assume that the user is a customer by default
        currentUserInfo.put(IS_CUSTOMER_FIELD, true);
        currentUserInfo.put(IS_ADMIN_FIELD, false);
        currentUserInfo.put(IS_HEALTHCARE_WORKER_FIELD, false);
    }

    @Override
    public String toString() {
        return currentUserInfo.toString();
    }
}

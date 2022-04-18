package users;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

/**
 * This class is like an agent that can be used by actors to contact with the system.(will be more security)
 * The current user is the user that is currently logged in. (might be the resident or the Administrators/receptionist ...)
 */
public abstract class User {

    // class name
    public static final String name = "users.CurrentUser";
    protected ObjectNode currentUserInfo;
    protected String token;

    protected static ObjectNode attributesTemplate = new ObjectMapper().createObjectNode();

    protected static final String IS_CUSTOMER_FIELD = "isCustomer";

    protected static final String IS_ADMIN_FIELD = "isAdmin";

    protected static final String IS_HEALTHCARE_WORKER_FIELD = "isHealthcareWorker";

    public static final String PHONE_NUMBER_FIELD = "phoneNumber";

    public static final String PASSWORD_FIELD = "password";

    public static final String USER_NAME_FIELD = "userName";

    public static final String FAMILY_NAME_FIELD = "familyName";

    public static final String GIVEN_NAME_FIELD = "givenName";

    //    private static HashMap<String, Object> attributesTemplate = new HashMap<>();
    static {
        // empty template for the attributes
        // update values when needed in other places of the code
        attributesTemplate.put(GIVEN_NAME_FIELD, "String");
        attributesTemplate.put(FAMILY_NAME_FIELD, "String");
        attributesTemplate.put(USER_NAME_FIELD, "String");
        attributesTemplate.put(PASSWORD_FIELD, "String");
        attributesTemplate.put(PHONE_NUMBER_FIELD, "String");
        attributesTemplate.put(IS_CUSTOMER_FIELD, false);
        attributesTemplate.put(IS_ADMIN_FIELD, false);
        attributesTemplate.put(IS_HEALTHCARE_WORKER_FIELD, false);

//        attributesTemplate.put("additionalInfo", new ObjectMapper().createObjectNode());

    }

    /**
     * used for user who is doing signup
     *
     * @param
     * @return return a JSON String converted from ObjectNode, which is the current user info that is going to be saved in the system
     */
    public String buildRequestBody() {
        // 1. the role of user will be set to true when the instance of the class is created

        // 2. update other data from user's input
        for (Iterator<String> it = attributesTemplate.fieldNames(); it.hasNext(); ) {
            String key = it.next();
            // the value of the key is the type of String
            if (attributesTemplate.get(key).isTextual()) {
                System.out.print(key + " : ");
                Scanner s = new Scanner(System.in);
                String value = s.next();
                attributesTemplate.put(key, value);
            }
        }

        return attributesTemplate.toString();
    }


    public User() {
    }

//
//    public String convertToJson() {
//        // 将Java对象转换为JSON对象
//        ObjectMapper mapper = new ObjectMapper();
//        String json = null;
//        try {
//            json = mapper.writeValueAsString(this);
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }
//        return json;
//    }




}

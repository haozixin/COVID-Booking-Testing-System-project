package users;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

/**
 * This class is like an agent that can be used by actors to contact with the system.(will be more security)
 * The current user is the user that is currently logged in. (might be the resident or the Administrators/receptionist ...)
 *
 */
public abstract class CurrentUser {

    // class name
    public static final String name = "users.CurrentUser";
    protected ObjectNode currentUserInfo;
    protected String token;

    protected static ObjectNode attributesTemplate = new ObjectMapper().createObjectNode();

    protected static final String IS_CUSTOMER_FIELD = "isCustomer";

    protected static final String IS_ADMIN_FIELD = "isAdmin";

    protected static final String IS_HEALTHCARE_WORKER_FIELD = "isHealthcareWorker";

    //    private static HashMap<String, Object> attributesTemplate = new HashMap<>();
    static {
        // empty template for the attributes
        // update values when needed in other places of the code
        attributesTemplate.put("givenName", "String");
        attributesTemplate.put("familyName", "String");
        attributesTemplate.put("userName", "String");
        attributesTemplate.put("password", "String");
        attributesTemplate.put("phoneNumber", "String");
        attributesTemplate.put(IS_CUSTOMER_FIELD, false);
        attributesTemplate.put(IS_ADMIN_FIELD, false);
        attributesTemplate.put(IS_HEALTHCARE_WORKER_FIELD, false);

//        attributesTemplate.put("additionalInfo", new ObjectMapper().createObjectNode());

    }

    /**
     *  used for user who is doing signup
     * @param
     * @return return a JSON String converted from ObjectNode, which is the current user info that is going to be saved in the system
     */
    protected String buildRequestBody(){
        // 1. the role of user will be set to true when the instance of the class is created

        // 2. update other data from user's input
        for (Iterator<String> it = attributesTemplate.fieldNames(); it.hasNext(); ) {
            String key = it.next();
            // the value of the key is the type of String
            if (attributesTemplate.get(key).isTextual()) {
                System.out.print(key+" : ");
                Scanner s = new Scanner(System.in);
                String value = s.next();
                attributesTemplate.put(key, value);
            }

        }

        return attributesTemplate.toString();
    }


    /**
     *  Constructor 1 - get an ObjectNode from outside
     * @param currentUserInfo ObjectNode of this user
     */
    public CurrentUser(ObjectNode currentUserInfo) {
        this.currentUserInfo = currentUserInfo;
    }

    public CurrentUser(){}


    public void setCurrentUserInfo(ObjectNode currentUserInfo) {
        this.currentUserInfo = currentUserInfo;
    }

    /**
     * Constructor 2 - get a token from outside
     * used for user who is doing login
     * @param token JSON web Token - get it from the system(issued by the system)
     */
    public CurrentUser(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    /*
        public String convertToJson() {
        // 将Java对象转换为JSON对象
        ObjectMapper mapper = new ObjectMapper();
        String json = null;
        try {
            json = mapper.writeValueAsString(this);
        }
        catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return json;
    }
     */

}

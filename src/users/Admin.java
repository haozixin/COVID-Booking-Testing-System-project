package users;

public class Admin extends User {
    protected static final String IS_ADMIN_FIELD = "isAdmin";
    static {
        attributesTemplate.put(IS_ADMIN_FIELD, true);
    }

}

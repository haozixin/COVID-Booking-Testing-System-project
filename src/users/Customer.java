package users;

public class Customer extends CurrentUser {
    protected static final String IS_CUSTOMER_FIELD = "isCustomer";
    static {
        attributesTemplate.put(IS_CUSTOMER_FIELD, true);
    }

}

package enums;

public enum UserRoles {
    IS_CUSTOMER_FIELD("isCustomer"),
    IS_ADMIN_FIELD("isReceptionist"),
    IS_HEALTHCARE_WORKER_FIELD("isHealthcareWorker");

    private final String role;
    UserRoles(String role) {
        this.role = role;
    }
    public String getName() {
        return role;
    }
    //get all
    public static String[] getAllRoles() {
        String[] roles = new String[UserRoles.values().length];
        int i = 0;
        for (UserRoles role : UserRoles.values()) {
            roles[i] = role.getName();
            i++;
        }
        return roles;
    }
}

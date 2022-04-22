package enums;

public enum Path {

    USER("/users"),
    SIGN_UP("/users"),
    USER_LOGIN("/users/login"),
    VERIFY_TOKEN("/users/verify-token"),
    SITE("/testing-site"),
    BOOKING("/booking"),
    TEST("/covid-test"),
    PHOTO("/photo")
    ;

    private final String path;
    Path(String path) {
        this.path = path;
    }
    public String getPath() {
        return path;
    }



}

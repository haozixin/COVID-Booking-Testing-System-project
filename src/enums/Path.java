package enums;

public enum Path {

    USER("/user"),
    SIGN_UP("/user"),
    USER_LOGIN("/user/login"),
    VERIFY_TOKEN("/user/verify-token"),
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

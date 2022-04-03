package LoginSubsystem;

public class CurrentUser {
    private String token;

    /**
     * Constructor
     * @param token JSON web Token - get it from the system(issued by the system)
     */
    public CurrentUser(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    //TODO:未来可能需要解析token的函数
}

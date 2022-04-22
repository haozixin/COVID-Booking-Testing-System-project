package enums;

public enum ResponseStatus {
    CODE_200(200, "The request succeeded"),
    CODE_201(201, "New object successfully created."),
    CODE_400(400,"Bad Request - invalid XX is provided"),
    CODE_404(404,"Not Fount"),
    CODE_204(204,"No Content - It was successfully deleted"),
    CODE_401(401,"A valid API key was not provided in the request."),
    CODE_409(409,"The request conflicts with the current state of the server - cannot be deleted if it has any associated table"),
    CODE_415(415,"Unsupported Media Type - Only JPEG and PNG files may be uploaded."),
    CODE_422(422,"Upload link cannot be generated as uploading further content may cause your team's file storage quota to be exceeded.")
    ;

    private final int code;
    private final String description;
    ResponseStatus(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public static String matchCode(int code){
        for (ResponseStatus i : ResponseStatus.values()){
            if (i.getCode()==code){
                return i.getDescription();
            }
        }
        return "Sorry, we don't know about this response code";
    }
}

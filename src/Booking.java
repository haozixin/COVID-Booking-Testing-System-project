public class Booking {
    public static final String name = "Booking";
    private String customerId;
    private String testingSiteId;

    /**
     * String dataType for dateTime is easier to map
     */
    private String startTime;
    private String notes;

    /**
     *  Constructor 1 for Booking class (mainly used for instances are created by user through input values)
     * @param customerId String dataType for customerId
     * @param siteId     String dataType for testingSiteId
     * @param notes      String dataType for notes
     * @param startTime      String dataType for startTime
     */
    public Booking(String customerId, String siteId, String notes, String startTime) {
        this.customerId = customerId;
        testingSiteId = siteId;
        this.notes = notes;
        this.startTime = startTime;
    }

    public Booking() {}


    public String buildRequestBody() {

        String requestBody = "{" +
                "\"customerId\": \"" + customerId + "\"," +
                "\"testingSiteId\": \"" + testingSiteId + "\"," +
                "\"startTime\": \"" + startTime + "\"," +
                "\"notes\": \"" + notes + "\"" +
                "}";
        return requestBody;
    }




}

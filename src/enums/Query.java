package enums;

/**
 * frequently used queries
 */
public enum Query {


    BOOKINGS_IN_USER_OR_SITE("?fields=bookings"),
    COVIDTESTS_IN_USER_OR_SITE("?fields=bookings.covidTests"),
    COVIDTESTS_IN_BOOKING("?fields=covidTests"),
    TESTSTAKEN_IN_USER("?fields=testsTaken"),
    TESTSADMINISTERED_IN_USER("user?fields=testsAdministered");


    private final String query;
    Query(String path) {
        this.query = path;
    }
    public String getQuery() {
        return query;
    }
}

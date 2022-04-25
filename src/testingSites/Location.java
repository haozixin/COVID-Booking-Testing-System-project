package testingSites;

import com.fasterxml.jackson.databind.node.ObjectNode;
import utility.Utility;

/**
 * Location class
 * Nested class in the testingSite class
 */
public class Location {
    public static final String SUBURB_FIELD = "suburb";
    private ObjectNode address;
    private final String name = "Address";

    /**
     * Constructor
     * @param jsonNode the jsonNode of the address
     */
    public Location(ObjectNode jsonNode) {
        this.address = jsonNode;
    }

    /**
     * Getter for the suburb
     * @return the suburb
     */
    public String getSuburb(){
        try {
            return address.get(SUBURB_FIELD).asText();
        } catch (NullPointerException e) {
            System.out.println("There is no relative suburb field in address!");
            return null;
        }
    }


    @Override
    public String toString() {
        return Utility.formatMessage(name, address);
    }
}

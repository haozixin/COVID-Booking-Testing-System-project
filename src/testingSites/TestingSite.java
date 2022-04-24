package testingSites;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import utility.Utility;

import java.util.HashMap;

public class TestingSite {
    public static final String ADDRESS_FIELD = "address";
    public static final String FACILITY_TYPE_FIELD = "facilityType";
    private final String name = "Testing Site";
    // store all information of testing site by using ObjectNode testingSite
    private ObjectNode testingSiteInfo;
    private Location location;





    public TestingSite(ObjectNode siteJsonNode) {
        this.testingSiteInfo = siteJsonNode;
        setLocation();
    }


    public void addSiteToWeb(){
        //TODO: add new testing site(might use it as test data) to web
    }

    private void setLocation(){
        try {
            JsonNode locationJsonNode = testingSiteInfo.get(ADDRESS_FIELD);
        } catch (NullPointerException e) {
            System.out.println("There is no testing site!");
            location = null;
        }
    }

    private Location getLocation() {
        return location;
    }

//    public String getSuburb(){
//        return location.getSuburb();
//    }

//    public String getFacilityType(){
//        try {
//            return testingSiteInfo.findValue(FACILITY_TYPE_FIELD).asText();
//        } catch (NullPointerException e) {
//            return null;
//        }
//    }

    public String findValue(String field){
        try {
            return testingSiteInfo.findValue(field).asText();
        } catch (NullPointerException e) {
            return null;
        }
    }

    private String getId(){
        return testingSiteInfo.get("id").asText();
    }

    /**
     * override toString method
     * @return a formated string value of testing site
     */
    @Override
    public String toString() {
        return Utility.displayMessage(name, testingSiteInfo);
    }

    /**
     *  override equals method
     *  (contains method and remove method in Collection class will call this method)
     * @param obj object to compare
     * @return boolean value, true if equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if((!(obj instanceof TestingSite))){
            return false;
        }
        // same instance
        if(obj==this){
            return true;
        }
        // if id is same, it is the same testing site
        TestingSite testingSite = (TestingSite) obj;
        return testingSite.getId().equals(this.getId());
    }
}

package searchForSitesSubsystem;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import utility.Utility;

public class TestingSite {
    public static final String ADDRESS_FIELD = "address";
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
        JsonNode locationJsonNode = testingSiteInfo.get(ADDRESS_FIELD);
        location = new Location((ObjectNode) locationJsonNode);
    }

    private Location getLocation() {
        return location;
    }

    private String getSuburb(){
        return location.getSuburb();
    }

    private String getId(){
        return testingSiteInfo.get("id").asText();
    }

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

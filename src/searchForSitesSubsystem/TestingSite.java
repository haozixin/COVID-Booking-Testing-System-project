package searchForSitesSubsystem;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import utility.Utility;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

public class TestingSite {
    public static final String ADDRESS_FIELD = "address";
    private final String name = "Testing Site";
    private ObjectNode testingSite;
    private Location location;


// {"id":"7fbd25ee-5b64-4720-b1f6-4f6d4731260e","name":"Monash University - Clayton","description":"RAT and PCR testing available. COVID testing available Monday to Friday by appointment. Pre-register online https://testtracker.covid19.dhhs.vic.gov.au/citizen-prefill; Then call 03 5871 0777 (option 6) to make your appointment time.","websiteUrl":null,"phoneNumber":"0358710777","address":{"latitude":-37,"longitude":145,"unitNumber":"133","street":"Wellington Road","street2":null,"suburb":"Clayton","state":"VIC","postcode":"3168","additionalInfo":{}},"createdAt":"2022-03-17T00:14:12.678Z","updatedAt":"2022-03-17T00:14:12.678Z","additionalInfo":{}}

    public TestingSite(ObjectNode siteJsonNode) {
        this.testingSite = siteJsonNode;
        setLocation();

    }


    public void addSiteToWeb(){
        //TODO: add new testing site(might use it as test data) to web
    }

    private void setLocation(){
        JsonNode locationJsonNode = testingSite.get(ADDRESS_FIELD);
        location = new Location((ObjectNode) locationJsonNode);
    }

    private Location getLocation() {
        return location;
    }

    private String getSuburb(){
        return location.getSuburb();
    }

    private String getId(){
        return testingSite.get("id").asText();
    }

    @Override
    public String toString() {
        return Utility.displayMessage(name, testingSite);
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

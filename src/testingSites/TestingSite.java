package testingSites;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import enums.Path;
import utility.Utility;
import webServiceAPI.ServicesAdapter;
import webServiceAPI.WebServicesTarget;

import java.io.IOException;
import java.util.HashMap;

public class TestingSite {
    public static final String ADDRESS_FIELD = "address";
    public static final String FACILITY_TYPE_FIELD = "facilityType";
    public static final String ADDITIONAL_INFO_FIELD = "additionalInfo";
    public static final String WAITING_TIMES_FIELD = "waitingTimes(min)";
    private final String name = "Testing Site";
    // store all information of testing site by using ObjectNode testingSite
    private ObjectNode testingSiteInfo;
    private ObjectNode additionalInfo;
    private Location location;





    public TestingSite(ObjectNode siteJsonNode) {
        this.testingSiteInfo = siteJsonNode;
        additionalInfo = siteJsonNode.get(ADDITIONAL_INFO_FIELD).deepCopy();
        setLocation();
    }


    public void addSiteToWeb(){
        //TODO: add new testing site(might use it as test data) to web
    }

    private void setLocation(){
        try {
            JsonNode locationJsonNode = testingSiteInfo.get(ADDRESS_FIELD);
            location = new Location((ObjectNode) locationJsonNode);
        } catch (NullPointerException e) {
            //System.out.println("For this testing site, the address field is null!");
            location = null;
        }
    }

    private Location getLocation() {
        return location;
    }


    public String findValue(String field){
        try {
            return testingSiteInfo.findValue(field).asText();
        } catch (NullPointerException e) {
            return null;
        }
    }

    public String getId(){
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
     *  as long as the testing site id is the same, the testing site is the same
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

    public void updateWaitingTime() throws IOException, InterruptedException {
        try{
            String value = testingSiteInfo.findValue(WAITING_TIMES_FIELD).asText();
            int newValue = Integer.parseInt(value)+5;
            additionalInfo.put(WAITING_TIMES_FIELD, newValue);
            testingSiteInfo.putPOJO(ADDITIONAL_INFO_FIELD, additionalInfo);
        }catch (NullPointerException e){
            System.out.println("Failed update waiting time for this Testing Site!");
        }

        // update the data to the server
        WebServicesTarget web = new ServicesAdapter();
        web.patchData(Path.SITE.getPath(), additionalInfo.toString(), getId());
    }
}

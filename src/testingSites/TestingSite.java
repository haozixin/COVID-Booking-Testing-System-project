package testingSites;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import engine.Entity;
import enums.Path;
import utility.Utility;
import webServiceAPI.ServicesAdapter;
import webServiceAPI.WebServicesTarget;

import java.io.IOException;

/**
 * TestingSite class
 * An Entity that represents a Testing Site
 */
public class TestingSite extends Entity {
    public static final String className = "Testing Site";
    public static final String ADDRESS_FIELD = "address";
    public static final String FACILITY_TYPE_FIELD = "facilityType";
    public static final String ADDITIONAL_INFO_FIELD = "additionalInfo";
    public static final String WAITING_TIMES_FIELD = "waitingTimes(min)";
    public static final String HAS_ON_SITE_BOOKING_FIELD = "hasOnSiteBooking";


    private Location location;

    /**
     * Constructor 1 of Entity - receives and wraps a json object
     * Used for deserialization - the json object is wrapped in an entity
     * @param siteJsonNode - the json object to be wrapped
     */
    public TestingSite(ObjectNode siteJsonNode) {
        super(siteJsonNode);
        setLocation();
    }


    /**
     * set the location of the Testing Site
     */
    private void setLocation(){
        try {
            JsonNode locationJsonNode = entityInfo.get(ADDRESS_FIELD);
            location = new Location((ObjectNode) locationJsonNode);
        } catch (NullPointerException e) {
            //System.out.println("For this testing site, the address field is null!");
            location = null;
        }
    }


    /**
     * Display the Testing Site in a readable format
     */
    public void display() {
        System.out.println(Utility.formatMessage(className, entityInfo));

    }


    @Override
    protected void initialSchema() {
        // haven't used for A2
    }

    /**
     * update Waiting Time for the Testing Site
     * @throws IOException
     * @throws InterruptedException
     */
    public void updateWaitingTime() throws IOException, InterruptedException {
        // update local data
        try{
            String value = entityInfo.findValue(WAITING_TIMES_FIELD).asText();
            int newValue = Integer.parseInt(value)+5;
            additionalInfo.put(WAITING_TIMES_FIELD, newValue);
            System.out.println("This site's waiting time becomes: " + newValue+" minutes");
            entityInfo.putPOJO(ADDITIONAL_INFO_FIELD, additionalInfo);
        }catch (NullPointerException e){
            System.out.println("Failed update waiting time for this Testing Site!");
        }

        // update the data to the server
        WebServicesTarget web = new ServicesAdapter();
        String json = Utility.buildNestedJson(ADDITIONAL_INFO_FIELD, additionalInfo.toString());
        web.patchData(Path.SITE.getPath(), json, getEntityId());
    }


}

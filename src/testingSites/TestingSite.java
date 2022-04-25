package testingSites;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import engine.Entity;
import enums.Path;
import utility.Utility;
import webServiceAPI.ServicesAdapter;
import webServiceAPI.WebServicesTarget;

import java.io.IOException;

public class TestingSite extends Entity {
    public static final String className = "Booking";
    public static final String ADDRESS_FIELD = "address";
    public static final String FACILITY_TYPE_FIELD = "facilityType";
    public static final String ADDITIONAL_INFO_FIELD = "additionalInfo";
    public static final String WAITING_TIMES_FIELD = "waitingTimes(min)";


    private Location location;

    public TestingSite(ObjectNode siteJsonNode) {
        super(siteJsonNode);
        setLocation();
    }



    private void setLocation(){
        try {
            JsonNode locationJsonNode = entityInfo.get(ADDRESS_FIELD);
            location = new Location((ObjectNode) locationJsonNode);
        } catch (NullPointerException e) {
            //System.out.println("For this testing site, the address field is null!");
            location = null;
        }
    }


    public String display() {
        return Utility.formatMessage(className, entityInfo);
    }


    @Override
    protected void initialSchema() {
        // haven't used for A2
    }


    public void updateWaitingTime() throws IOException, InterruptedException {
        // update local data
        try{
            String value = entityInfo.findValue(WAITING_TIMES_FIELD).asText();
            int newValue = Integer.parseInt(value)+5;
            additionalInfo.put(WAITING_TIMES_FIELD, newValue);
            entityInfo.putPOJO(ADDITIONAL_INFO_FIELD, additionalInfo);
        }catch (NullPointerException e){
            System.out.println("Failed update waiting time for this Testing Site!");
        }

        // update the data to the server
        WebServicesTarget web = new ServicesAdapter();
        web.patchData(Path.SITE.getPath(), additionalInfo.toString(), getEntityId());
    }
}

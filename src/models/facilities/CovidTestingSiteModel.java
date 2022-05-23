package models.facilities;

import com.fasterxml.jackson.databind.node.ObjectNode;
import enums.Path;
import models.EntityModel;
import utility.Utility;
import webServiceAPI.ServicesAdapter;
import webServiceAPI.WebServicesTarget;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class CovidTestingSiteModel extends EntityModel {
    public static final String className = "Testing Site";
    public static final String ADDRESS_FIELD = "address";
    public static final String FACILITY_TYPE_FIELD = "facilityType";
    public static final String ADDITIONAL_INFO_FIELD = "additionalInfo";
    public static final String WAITING_TIMES_FIELD = "waitingTimes(min)";
    public static final String HAS_ON_SITE_BOOKING_FIELD = "hasOnSiteBooking";
    public static final String SUBURB_FIELD = "suburb";
    public static final int ADDITIONAL_WAITING_TIME = 5;
    private FacilityType facilityType;


    /**
     * update Waiting Time for the Testing Site
     * the sub-field is under the additionalInfo field
     *
     * @throws IOException
     * @throws InterruptedException
     */
    public void updateWaitingTime(String siteId) throws IOException, InterruptedException {
        getSpecifiedEntity(Path.SITE.getPath(), siteId, null);

        // update local data
        try {
            String value = entityInfo.findValue(WAITING_TIMES_FIELD).asText();
            int newValue = Integer.parseInt(value) + ADDITIONAL_WAITING_TIME;
            additionalInfo.put(WAITING_TIMES_FIELD, newValue);
            entityInfo.putPOJO(ADDITIONAL_INFO_FIELD, additionalInfo);
        } catch (NullPointerException e) {
            // if the field is not existent, create it
            additionalInfo.put(WAITING_TIMES_FIELD, ADDITIONAL_WAITING_TIME);
            entityInfo.putPOJO(ADDITIONAL_INFO_FIELD, additionalInfo);
        }

        // update the data to the server
        try{
            String json = Utility.buildNestedJson(ADDITIONAL_INFO_FIELD, additionalInfo.toString());
            webServicesTarget.patchData(Path.SITE.getPath(), json, siteId);
        }catch (NullPointerException e){
            responseMessage = webServicesTarget.getResponseMessage();
        }
    }

    public String getWaitingTime(){
        try{
            return additionalInfo.findValue(WAITING_TIMES_FIELD).asText();
        }catch (NullPointerException e){
            // if the field is not existent, it means you are the first one
            return "0";
        }
    }

    public CovidTestingSiteModel() {
    }

    public void getFacilityById(String id) throws IOException, InterruptedException {
        getSpecifiedEntity(Path.SITE.getPath(), id, null);
    }

    public String getId(){
        return entityInfo.get("id").asText();
    }

}

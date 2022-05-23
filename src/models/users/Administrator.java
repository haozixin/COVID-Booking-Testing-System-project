package models.users;

import com.fasterxml.jackson.databind.node.ObjectNode;
import enums.Path;
import models.facilities.CovidTestingSiteModel;
import webServiceAPI.ServicesAdapter;
import webServiceAPI.WebServicesTarget;

import java.io.IOException;

public class Administrator extends UserType{
    private CovidTestingSiteModel facility;

    /**
     * Constructor for Administrator
     * initializes the facility using the facility id stored in User's additionalInfo field
     * @param data - the data of the user(ObjectNode)
     */
    public Administrator(ObjectNode data) {
        String facility = data.findValue("workAt(siteId)").asText();

        CovidTestingSiteModel facilityModel = new CovidTestingSiteModel();
        try {
            facilityModel.getFacilityById(facility);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        this.facility = facilityModel;

    }

    @Override
    public String getFacilityId() {
        return facility.getId();
    }

}

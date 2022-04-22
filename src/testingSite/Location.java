package testingSite;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import utility.Utility;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

public class Location {
    public static final String SUBURB_FIELD = "suburb";
    private ObjectNode address;
    private final String name = "Address";

// {"id":"7fbd25ee-5b64-4720-b1f6-4f6d4731260e","name":"Monash University - Clayton","description":"RAT and PCR testing available. COVID testing available Monday to Friday by appointment. Pre-register online https://testtracker.covid19.dhhs.vic.gov.au/citizen-prefill; Then call 03 5871 0777 (option 6) to make your appointment time.","websiteUrl":null,"phoneNumber":"0358710777","address":{"latitude":-37,"longitude":145,"unitNumber":"133","street":"Wellington Road","street2":null,"suburb":"Clayton","state":"VIC","postcode":"3168","additionalInfo":{}},"createdAt":"2022-03-17T00:14:12.678Z","updatedAt":"2022-03-17T00:14:12.678Z","additionalInfo":{}}

    public Location(ObjectNode jsonNode) {
        this.address = jsonNode;
    }

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
        return Utility.displayMessage(name, address);
    }
}

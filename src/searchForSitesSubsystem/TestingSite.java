package searchForSitesSubsystem;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import utility.Utility;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

public class TestingSite {
    private final String name = "Testing Site";
    private ObjectNode testingSite;

// {"id":"7fbd25ee-5b64-4720-b1f6-4f6d4731260e","name":"Monash University - Clayton","description":"RAT and PCR testing available. COVID testing available Monday to Friday by appointment. Pre-register online https://testtracker.covid19.dhhs.vic.gov.au/citizen-prefill; Then call 03 5871 0777 (option 6) to make your appointment time.","websiteUrl":null,"phoneNumber":"0358710777","address":{"latitude":-37,"longitude":145,"unitNumber":"133","street":"Wellington Road","street2":null,"suburb":"Clayton","state":"VIC","postcode":"3168","additionalInfo":{}},"createdAt":"2022-03-17T00:14:12.678Z","updatedAt":"2022-03-17T00:14:12.678Z","additionalInfo":{}}

    public TestingSite(ObjectNode jsonNode) {
        this.testingSite = jsonNode;
    }

    public ArrayList<String> displayMessage() {
        return Utility.displayMessage(name, testingSite);
    }


}

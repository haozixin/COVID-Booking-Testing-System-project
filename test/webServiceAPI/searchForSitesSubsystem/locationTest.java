package webServiceAPI.searchForSitesSubsystem;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.Test;
import testingSites.Location;

public class locationTest {

    @Test
    public void displayMessage() throws JsonProcessingException {
        String json2 = "{\"name\":\"Allen\",\"age\":18,\"set\":{\"tel\":1591851786568, \"address\":\"深圳市南山区科技园\"}}";
        ObjectNode testData = new ObjectMapper().readValue(json2, ObjectNode.class);
        Location loc = new Location(testData);
        System.out.println(loc);


    }

    @Test
    public void getSuburb() throws JsonProcessingException {
        // test-data
        String json = "{ \"latitude\": -37, " +
                "\"longitude\": 145, " +
                "\"unitNumber\": \"533\", " +
                "\"street\": \"Blackburn Road\", " +
                "\"street2\": null, " +
                "\"suburb\": \"Mount Waverley\"," +
                "\"state\": \"VIC\"," +
                "\"postcode\": \"3149\", " +
                "\"additionalInfo\": {}}";

        ObjectNode testData = new ObjectMapper().readValue(json, ObjectNode.class);
        Location loc = new Location(testData);
        System.out.println(loc.getSuburb());
//        System.out.println(testData.get("longitude"));

    }
}
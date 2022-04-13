package searchForSitesSubsystem;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class locationTest {

    @Test
    public void displayMessage() throws JsonProcessingException {
        String json2 = "{\"name\":\"Allen\",\"age\":18,\"set\":{\"tel\":1591851786568, \"address\":\"深圳市南山区科技园\"}}";
        ObjectNode testData = new ObjectMapper().readValue(json2, ObjectNode.class);
        location loc = new location(testData);
        ArrayList<String>message = loc.displayMessage();
        for (String s:message) {
            System.out.println(s);
        }


    }
}
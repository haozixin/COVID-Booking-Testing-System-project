package utility;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

public class Utility{
    public static Properties myProp = new Properties();
    public static InputStream myResource = Utility.class.getResourceAsStream("/myConfig.properties");
    static {
        try {
            myProp.load(myResource);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static String getMyConf(String props) {
        return myProp.getProperty(props);
    }

    public static ArrayList<String> displayMessage(String name, ObjectNode jsonNode) {
        ArrayList<String> message = new ArrayList<>();
        message.add("|----------------------------"+name+"--------------------------|");
        // display a formatted message
        Iterator<Map.Entry<String, JsonNode>> temp = jsonNode.fields();
        for (Iterator<Map.Entry<String, JsonNode>> it = temp; it.hasNext(); ) {
            Map.Entry<String, JsonNode> entry = it.next();
            message.add(entry.getKey() + " ==> " + entry.getValue());
        }
        return message;
    }



}
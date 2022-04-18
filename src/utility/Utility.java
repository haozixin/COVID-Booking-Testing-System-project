package utility;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class Utility {
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

    public static String displayMessage(String name, JsonNode jsonNode) {
        StringBuilder message2 = new StringBuilder();
        ArrayList<String> message = new ArrayList<>();
        message2.append("|----------------------------").append(name).append("--------------------------|\n");
        // display a formatted message
        Iterator<Map.Entry<String, JsonNode>> temp = jsonNode.fields();
        for (Iterator<Map.Entry<String, JsonNode>> it = temp; it.hasNext(); ) {
            Map.Entry<String, JsonNode> entry = it.next();
            message2.append(entry.getKey()).append(" ==> ").append(entry.getValue()).append("\n");
        }

        return message2.toString();
    }

    public static void printArrayList(ArrayList<String> message) {
        for (String s : message) {
            System.out.println(s);
        }
    }

    /**
     * used for the Services.java, show a formatted error message
     * @param response response body (only for errors respond) from the server
     */
    public static void resolveError(ObjectNode response){
    	System.out.println("Error in the request: ");
    	response.get("error").forEach(System.out::println);
        System.out.println("Tips: ");
        response.get("message").forEach(System.out::println);
    }



}
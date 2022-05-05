package utility;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * Utility class for common methods.
 */
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

    /**
     *  Get the value of a property from the properties file
     * @param props the properties file
     * @return the value of the property
     */
    public static String getMyConf(String props) {
        return myProp.getProperty(props);
    }

    /**
     * Display the json object in a formatted way
     * @param jsonNode  the json object
     * @return return the formatted string
     */
    public static String formatMessage(JsonNode jsonNode) {
        StringBuilder message = new StringBuilder();
        // display a formatted message
        Iterator<Map.Entry<String, JsonNode>> temp = jsonNode.fields();
        for (Iterator<Map.Entry<String, JsonNode>> it = temp; it.hasNext(); ) {
            Map.Entry<String, JsonNode> entry = it.next();
            message.append(entry.getKey()).append(" ==> ").append(entry.getValue()).append("\n");
        }

        return message.toString();
    }

    /**
     *  Display the json object(value is a list and Nested json object) in a formatted way - [{}...{}]
     * @param jsonNode the json object
     */
    public static void displayJsonList(JsonNode jsonNode){
        if (jsonNode != null) {
            for (JsonNode node : jsonNode) {
                System.out.println(formatMessage( node));
            }
        }
        else{
            System.out.println("There is no data to print");
        }
    }


    /**
     * Display the ArrayList in a formatted way - [..., ..., ...]
     * @param message An ArrayList
     */
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

    /**
     * Generate a DashBoard/Banner for the system (can be reused by all kinds of system)
     *
     * @param width  the width you want
     * @param height the hdight you want
     * @return return ArrayList<String> contains the dashboard that needs to display
     */
    public static ArrayList<String> setDashboard(int width, int height, String title_name) {
        String titleText = "Welcome to " + title_name;
        ArrayList<String> dashBoard = new ArrayList<>();
        String a = "=";
        String side = "===";
        for (int x = 0; x < height; x++) {
            String line = a;
            if (x == 0 || x == height - 1) {
                for (int y = (2 * (a.length())); y < width; y++) {
                    line = line.concat(a);
                }
            } else if (x != height / 2) {
                int leftPadding = width - (2 * side.length()) - 1;
                line = String.format(side + "%" + leftPadding + "s", "");
                line = line.concat(side);
            }

            if (x == height / 2) {
                int leftPadding = (width - side.length()) * 3 / 5;
                String title = String.format(side + "%" + leftPadding + "s", titleText);

                int rightPadding = (width - side.length()) - 1;
                title = String.format("%1$-" + rightPadding + "s" + side, title);
                line = title;
            }

            dashBoard.add(line);
        }
        return dashBoard;
    }

    /**
     * Display what is the users' operation they are doing (can be reused by all kinds of system)
     * @param name
     */
    public static void displayAction(String name){
        System.out.println("----------You are doing "+name+"---------");
    }

    public static String buildNestedJson(String fieldName, String value){
        return "{\"" + fieldName +  "\": " +value+  "}";
    }


}
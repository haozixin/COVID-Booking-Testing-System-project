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
    public static String formatMessage(JsonNode jsonNode, String prefix){
        StringBuilder message = new StringBuilder();
        // display a formatted message
        Iterator<Map.Entry<String, JsonNode>> temp = jsonNode.fields();
        String arrow = " ==> ";
        for (Iterator<Map.Entry<String, JsonNode>> it = temp; it.hasNext(); ) {
            Map.Entry<String, JsonNode> entry = it.next();
            message.append(prefix);
            if(entry.getValue().isObject()){

                message.append(entry.getKey()).append(": \n{").append(formatMessage(entry.getValue(),"    ")).append("}\n");

            }
            else if(entry.getValue().isArray()){
                message.append(entry.getKey()).append(": \n{").append(displayJsonList(entry.getValue())).append("}\n");
            }
            else{

                message.append(entry.getKey()).append(arrow).append(entry.getValue()).append("\n");
            }
        }

        return message.toString();
    }

    /**
     *  Display the json object(value is a list and Nested json object) in a formatted way - [{}...{}]
     * @param jsonNode the json object
     */
    public static String displayJsonList(JsonNode jsonNode){
        StringBuilder message = new StringBuilder();

        if (jsonNode != null) {
            for (JsonNode node : jsonNode) {
                message.append("----------------------------------------------\n");
                message.append(formatMessage(node, "    "));
                message.append("\n");
            }

            return message.toString();
        }
        else{
            return null;
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
    public static String resolveError(ObjectNode response){
        StringBuilder message = new StringBuilder();

        message.append(response.get("statusCode").asText()).append(": ");
        message.append(response.get("error").asText()).append("\n");
        message.append("Tips: \n");
        message.append(response.get("message").asText());
        System.out.println(message.toString());
        return message.toString();
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

    public static String buildPatchString(String fieldName, String value){
        return "{\"" + fieldName +  "\": \"" +value+ "\"}";
    }


}
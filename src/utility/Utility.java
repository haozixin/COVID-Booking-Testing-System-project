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



}
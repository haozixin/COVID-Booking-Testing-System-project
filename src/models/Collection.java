package models;

import com.fasterxml.jackson.databind.node.ObjectNode;
import enums.Path;
import utility.Utility;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Collection extends Model {
    public static final String FILTER_IS_ALL = "all";
    public ArrayList<ObjectNode> collection;


    /**
     * contains distinguish values of fields with the label(field name) used for filtering
     * e.g. if field name is "country", then the value of "country" is "USA","UK"...
     */
    public ArrayList<HashMap<String, String>> factors;

    public ArrayList<HashMap<String, String>> getFactors() {
        return factors;
    }

    public Collection() {
        collection = new ArrayList<>();
        factors = new ArrayList<>();
    }

    private void getAllEntity(String path) {

        try {
            ObjectNode[] sites = webServicesTarget.getAllData(path, null);
            responseMessage = webServicesTarget.getResponseMessage();
            collection.addAll(Arrays.asList(sites));
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

    }

    public String display(){
        StringBuilder list = new StringBuilder();
        if (collection.size() > 0) {
            for(ObjectNode node : collection) {
                list.append("-----------------------------------------------------\n");
                list.append(Utility.formatMessage(node));
            }
            return list.toString();
        }
        else{
            return "No data found";
        }
    }

    /**
     * filterFields is an array of strings that represent the fields that will be used for filtering (for example: suburb, type of facility...)
     * factors is an array of hashmaps that represent the values that will be used for filtering (for example: "suburb" has values "Melbourne", "Sydney"...)
     *
     * the function is to initialize the factors arrays(put value that gets from the whole data)
     *
     */
    public void initFilterFields(ArrayList<String> filterFields) {

        for (String field : filterFields) {
            HashMap<String, String> keyMap = new HashMap<String, String>();
            keyMap.put(FILTER_IS_ALL, field);
            for (ObjectNode entity : collection) {
                try{
                    if (entity.findValue(field).asText() != null) {
                        keyMap.put(entity.findValue(field).asText(), field);// for example: keyMap.put("USA", "country"); label(country) is in the place of value of the keymap
                    }
                }catch (NullPointerException e){
                }
            }
            factors.add(keyMap);
        }
    }

    /**
     * Get the testing site by suburb name / type of facility (such as Drive Through, Walk-in, Clinics, GPs or Hospitals)
     * if user input is "all", return all the testing sites
     *
     * @param field ArrayList<String, String> where the key is the name of the field(user want to filter by which field) and the value is the value of the field(user's input)
     * @return filteredList,  an array list of testing sites that meets the filter
     */
    public ArrayList<ObjectNode> filterByOnFactor(String field, String value) {

        if (value.equals(FILTER_IS_ALL)) {
            return collection;
        }

        ArrayList<ObjectNode> filteredList = new ArrayList<>();
        for (ObjectNode entity : collection) {
            // if testingSite.findValue(field) is null, then the testing site doesn't have the field
            if (entity.findValue(field) != null ){
                if (entity.findValue(field).asText().equals(value)) {
                    filteredList.add(entity);
                }
            }
        }
        return filteredList;
    }

    public void setCollection(ArrayList<ObjectNode> collection) {
        this.collection = collection;
    }

    public void updateCollection(String path){
        collection.clear();
        getAllEntity(path);
    }





}

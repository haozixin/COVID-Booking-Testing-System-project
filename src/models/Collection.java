package models;

import com.fasterxml.jackson.databind.node.ObjectNode;
import enums.Path;
import utility.Utility;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Collection extends Model {
    public ArrayList<ObjectNode> collection;

    public Collection() {
        this.collection = new ArrayList<>();
    }

    public void getAllSites(){

        try {
            ObjectNode[] sites = webServicesTarget.getAllData(Path.SITE.getPath(), null);
            responseMessage = webServicesTarget.getResponseMessage();
            collection.addAll(Arrays.asList(sites));
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

    }

    public String display(){
        StringBuilder list = new StringBuilder();
        for(ObjectNode node : collection) {
            list.append("-----------------------------------------------------\n");
            list.append(Utility.formatMessage(node));
        }
        return list.toString();
    }

}

package models;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import webServiceAPI.ServicesAdapter;
import webServiceAPI.WebServicesTarget;

import java.io.IOException;

public abstract class Model {


    public static final String ADDITIONAL_INFO_FIELD = "additionalInfo";
    protected ObjectNode entityInfo = new ObjectMapper().createObjectNode();
    protected ObjectNode additionalInfo = new ObjectMapper().createObjectNode();
    protected WebServicesTarget webServicesTarget = new ServicesAdapter();
    // if request is successful, responseMessage will be "", otherwise it will be the error message
    protected String responseMessage = "";



    public String getResponseMessage() {
        return responseMessage;
    }

    /**
     * Get the entity info from the server based on id
     * Error message is set in the responseMessage attribute
     * result will be stored in the entityInfo attribute
     *
     * @param path the path/url to the entity
     * @param id the id of the entity
     * @param query the query to be sent to the server
     * @throws IOException
     * @throws InterruptedException
     */
    protected void getSpecifiedEntity(String path, String id, String query) throws IOException, InterruptedException {
        ObjectNode node = webServicesTarget.getSpecificData(path, id, query);
        if (node != null) {
            entityInfo = node;
            additionalInfo = node.get(ADDITIONAL_INFO_FIELD).deepCopy();
        }
        responseMessage = webServicesTarget.getResponseMessage();
    }

    public void postModelToServer(String path) throws IOException, InterruptedException {
        try{
            // post to server and update local model at the same time
            ObjectNode result = webServicesTarget.postData(path, entityInfo.toString());
            entityInfo = result;
        }catch (Exception e){
            e.printStackTrace();
            responseMessage = webServicesTarget.getResponseMessage();
        }

    }
}

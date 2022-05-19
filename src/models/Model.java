package models;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import webServiceAPI.ServicesAdapter;
import webServiceAPI.WebServicesTarget;

import java.io.IOException;

/**
 * Model class for the application.
 * Under the class, it has two kind of models: collectionModel and entityModel.
 * i.e. the collectionModel is the model for a collection of data(like all records in database)
 * and the singleModel is the model for a single data(single record in database).
 */
public abstract class Model {



    protected WebServicesTarget webServicesTarget = new ServicesAdapter();
    // if request is successful, responseMessage will be "", otherwise it will be the error message
    // the message will be used to show on the UI(View)
    protected String responseMessage = "";



    public String getResponseMessage() {
        return responseMessage;
    }



}

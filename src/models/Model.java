package models;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import webServiceAPI.ServicesAdapter;
import webServiceAPI.WebServicesTarget;

public abstract class Model {

    protected ObjectNode entityInfo = new ObjectMapper().createObjectNode();
    protected ObjectNode additionalInfo = new ObjectMapper().createObjectNode();
    protected WebServicesTarget webServicesTarget = new ServicesAdapter();



}

package engine;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import testingSites.TestingSite;

import java.util.Iterator;
import java.util.Scanner;

public abstract class Entity {
    protected String className;
    protected ObjectNode entityInfo;
    protected ObjectNode additionalInfo = new ObjectMapper().createObjectNode();
    public static final String ADDITIONAL_INFO_FIELD = "additionalInfo";
    public static final String ID_FIELD = "id";


    /**
     * Constructor 1 of Entity - receives and wraps a json object
     * Used for deserialization - the json object is wrapped in an entity
     * @param entityInfo
     */
    public Entity(ObjectNode entityInfo) {
        this.entityInfo = entityInfo;
        try {
            additionalInfo = additionalInfo.get(ADDITIONAL_INFO_FIELD).deepCopy();
        } catch (NullPointerException e) {
        }
    }

    /**
     * Constructor 2 of Entity
     * Used for serialization - prepare a json object that will be passed to the web server through HTTP protocol
     */
    public Entity(){}

    public String getEntityId(){
        try {
            return entityInfo.get(ID_FIELD).asText();
        } catch (NullPointerException e) {
            return null;
        }
    }

    /**
     * Useful when printing current entity's info
     * @return
     */
    @Override
    public String toString() {
        return entityInfo.toString();
    }

    public abstract String display();

    /**
     *  override equals method
     *  as long as the entities' id is the same, they are same
     *  (contains method and remove method in Collection class will call this method)
     * @param obj object to compare
     * @return boolean value, true if equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if((!(obj instanceof Entity))){
            return false;
        }
        // same instance
        if(obj==this){
            return true;
        }
        // if id is same, it is the same testing site
        Entity entity = (Entity) obj;
        return entity.getEntityId().equals(this.getEntityId());
    }

    /**
     * Build a request body (For Post request)
     * Go through the template(created by initialSchema()) and get the value of each attribute from user input
     * <p>
     * It can avoid errors that caused by the missing required attributes.
     * and we don't have to change everywhere outside if there are more or less fields required on Web side)
     *
     */
    protected void addValueToSchema(){
        // 1. update data in schema using user's input
        for (Iterator<String> it = entityInfo.fieldNames(); it.hasNext(); ) {
            String key = it.next();
            // only set those data -- is the type of String
            if (entityInfo.get(key).isTextual()) {
                System.out.print(key + " : ");
                Scanner s = new Scanner(System.in);
                String value = s.next();
                entityInfo.put(key, value);
            }
        }
    }

    /**
     * Add the required attributes to the entityInfo
     * before let the user to create a new entity, we need to set a template(which attributes are required) for it
     * When user create a new object, the attributes will be reset based on the template
     * So, if there are more attributes required, we just add them here
     */
    protected abstract void initialSchema();

    protected String getEntityInfo(){
        return entityInfo.toString();
    }

    public String findValue(String field){
        try {
            return entityInfo.findValue(field).asText();
        } catch (NullPointerException e) {
            return null;
        }
    }


}

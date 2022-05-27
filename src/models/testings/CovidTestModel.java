package models.testings;


import models.EntityModel;
import utility.Utility;

/**
 * Create the CovidTestModel class
 */
public class CovidTestModel extends EntityModel {
    public boolean isCreated = false;
    public static final String PATIENT_ID_FIELD = "patientId";
    public static final String TYPE_FIELD = "type";
    public static final String ADMINISTER_ID_FIELD = "administerId";
    public static final String BOOKING_ID_FIELD = "bookingId";
    private CovidTestType type;

    public String display(){
        return Utility.formatMessage(entityInfo, "");
    }

    public void setSchema(String type, String patientId, String administerId, String bookingId) {
        entityInfo.put(TYPE_FIELD, type);
        entityInfo.put(PATIENT_ID_FIELD, patientId);
        entityInfo.put(ADMINISTER_ID_FIELD, administerId);
        entityInfo.put(BOOKING_ID_FIELD, bookingId);
    }


}

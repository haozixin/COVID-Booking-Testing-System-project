package testings;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import engine.Entity;
import enums.Path;
import webServiceAPI.ServicesAdapter;
import webServiceAPI.WebServicesTarget;

import java.io.IOException;

/**
 * CovidTest class
 * Used to store or create new data for CovidTest
 */
public class CovidTest extends Entity {
    public static final String PATIENT_ID_FIELD = "patientId";
    private static final String className = "CovidTest";
    public static final String TYPE_FIELD = "type";
    public static final String ADMINISTER_ID_FIELD = "administerId";
    public static final String BOOKING_ID_FIELD = "bookingId";
    private ICovidTestingType pcrTesting;
    private ICovidTestingType ratTesting;
    private ObjectNode testingData;

    public CovidTest(ObjectNode testingData) {
        this.testingData = testingData;
    }

    @Override
    public void display() {
    }

    @Override
    protected void initialSchema() {

    }

    public CovidTest(String type, String patientId, String administerId, String bookingId) {
        testingData = new ObjectMapper().createObjectNode();
        testingData.put(TYPE_FIELD, type);
        testingData.put(PATIENT_ID_FIELD, patientId);
        testingData.put(ADMINISTER_ID_FIELD, administerId);
        testingData.put(BOOKING_ID_FIELD, bookingId);
    }

    public void postTestingData() throws IOException, InterruptedException {
        WebServicesTarget ws = new ServicesAdapter();
        ws.postData(Path.TEST.getPath(), testingData.toString());
    }






}

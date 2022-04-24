package testings;

import com.fasterxml.jackson.databind.node.ObjectNode;
import webServiceAPI.WebServicesTarget;

public class CovidTest {
    private static final String name = "CovidTest";
    private ICovidTestingType pcrTesting;
    private ICovidTestingType ratTesting;
    private ObjectNode testingData;

    public CovidTest(ObjectNode testingData) {
        pcrTesting = new PCR();
        ratTesting = new RAT();
        this.testingData = testingData;
    }

    public void updateTestType(String type , String id){

    }


}

package testing;

import com.fasterxml.jackson.databind.node.ObjectNode;

public class CovidTest {
    private static final String name = "CovidTest";
    private ICovidTestingType testingType;
    private ObjectNode testingData;

    public CovidTest(ObjectNode testingData) {
        this.testingData = testingData;
    }

}

package testingSites;

import com.fasterxml.jackson.databind.node.ObjectNode;
import engine.DataSubscriber;
import enums.Path;
import webServiceAPI.ServicesAdapter;
import webServiceAPI.WebServicesAdaptee;
import webServiceAPI.WebServicesTarget;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class SitesCollection implements DataSubscriber {
    private static final String name = "TestingSites Collection";
    public static final String FILTER_IS_ALL = "all";
    private final ArrayList<TestingSite> testingSites = new ArrayList<>();
    private static SitesCollection instance = null;


    private ArrayList<HashMap<String, String>> factors;
    // factors used for filtering, if more factors are needed, only need to add them(Field name) into filterFields in constructor
    private ArrayList<String> filterFields;




    private SitesCollection() {
        factors = new ArrayList<>();
        filterFields = new ArrayList<>();

        filterFields.add(Location.SUBURB_FIELD);
        filterFields.add(TestingSite.FACILITY_TYPE_FIELD);
    }

    public static SitesCollection getInstance() {
        if (instance == null) {
            instance = new SitesCollection();
        }
        return instance;
    }

    public void addTestingSite(TestingSite testingSite) {
        testingSites.add(testingSite);
    }

    public void removeTestingSite(TestingSite testingSite) {
        testingSites.remove(testingSite);
    }

    public TestingSite getTestingSite(int index) {
        return testingSites.get(index);
    }

    public int getTestingSitesCount() {
        return testingSites.size();
    }

    private void clearTestingSites() {
        testingSites.clear();
    }


    public ArrayList<TestingSite> getTestingSites() {
        return testingSites;
    }


    /**
     * Update the jsonNodes array with the new data from the web service
     *
     * @return true if the update was successful, false otherwise
     * @throws IOException
     * @throws InterruptedException
     */
    @Override
    public void update() throws IOException, InterruptedException {
        WebServicesTarget ws = new ServicesAdapter();
        ObjectNode[] objectNode = ws.getAllData(Path.SITE.getPath(), null);
        // remove all the testing sites(old data)
        clearTestingSites();
        // add the new testing sites
        for (ObjectNode node : objectNode) {
            TestingSite testingSite = new TestingSite(node);
            addTestingSite(testingSite);
        }
        initFilterFields();
    }

    public void printList() {
        for (TestingSite testingSite : testingSites) {
            System.out.println(testingSite);
        }
    }

    /**
     * filterFields is an array of strings that represent the fields that will be used for filtering
     * factors is an array of hashmaps that represent the values that will be used for filtering
     *
     * the function is to initialize thefactors arrays(put value that gets from the whole data)
     *
     */
    private void initFilterFields() {

        for (String field : filterFields) {
            HashMap<String, String> keyMap = new HashMap<String, String>();
            keyMap.put(FILTER_IS_ALL, field);
            for (TestingSite testingSite : testingSites) {
                if (testingSite.findValue(field) != null) {
                    keyMap.put(testingSite.findValue(field), field);
                }
            }
            factors.add(keyMap);
        }
//        HashMap<String, String> keyToSuburbMap = new HashMap<String, String>();
//        HashMap<String, String> keyToFacilityTypeMap = new HashMap<String, String>();
//
//        keyToSuburbMap.put("all",Location.SUBURB_FIELD);
//        keyToFacilityTypeMap.put("all",TestingSite.FACILITY_TYPE_FIELD);
//
//
//        for (TestingSite testingSite : testingSites) {
//            String value = testingSite.getSuburb();
//
//            // Eliminate the non-existent and prevent duplication
//            if (value != null) {
//                keyToSuburbMap.put( value, Location.SUBURB_FIELD);
//            }
//            value = testingSite.getFacilityType();
//            if (value != null) {
//                keyToFacilityTypeMap.put(value, TestingSite.FACILITY_TYPE_FIELD);
//            }
//
//        }
//
//        factors.add(keyToSuburbMap);
//        factors.add(keyToFacilityTypeMap);
    }


    public void displayFactors() {
        for (HashMap<String, String> hashMap : factors) {
            System.out.println(hashMap);
        }
    }

    /**
     * Get the testing site by suburb name / type of facility (such as Drive Through, Walk-in, Clinics, GPs or Hospitals)
     * if user input is "all", return all the testing sites
     *
     * @param field ArrayList<String, String> where the key is the name of the field(user want to filter by which field) and the value is the value of the field(user's input)
     * @return filteredList,  an array list of testing sites that meets the filter
     */
    public ArrayList<TestingSite> filter(String field, String value) {

        if (value.equals(FILTER_IS_ALL)) {
            return testingSites;
        }

        ArrayList<TestingSite> filteredList = new ArrayList<>();
        for (TestingSite testingSite : testingSites) {
            // if testingSite.findValue(field) is null, then the testing site doesn't have the field
            if (testingSite.findValue(field) != null ){
                if (testingSite.findValue(field).equals(value)) {
                    filteredList.add(testingSite);
                }
            }
        }
        return filteredList;
    }



    public ArrayList<HashMap<String, String>> getFactors() {
        return factors;
    }

    /**
     *  update the waiting time field of the testing site
     *  update local testing site list and then patch the data to the server to make the consistency
     * @param siteId the id of the testing site
     */
    public void updateWaitingTime(String siteId) throws IOException, InterruptedException {
        for (TestingSite testingSite : testingSites) {
            if (testingSite.getId().equals(siteId)) {
                testingSite.updateWaitingTime();
            }
        }

    }


}

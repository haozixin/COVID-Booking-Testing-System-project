package testingSites;

import com.fasterxml.jackson.databind.node.ObjectNode;
import enums.Path;
import webServiceAPI.Services;
import webServiceAPI.WebServices;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class SitesCollection {
    private static final String name = "TestingSites Collection";
    private ArrayList<TestingSite> testingSites = new ArrayList<>();
    private static SitesCollection instance = null;

    private SitesCollection() {
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
     * @throws IOException
     * @throws InterruptedException
     * @return true if the update was successful, false otherwise
     */
    public void update() throws IOException, InterruptedException {
        WebServices ws = new Services();
        ObjectNode[] objectNode = ws.getAllData(Path.SITE.getPath());
        // remove all the testing sites(old data)
        clearTestingSites();
        // add the new testing sites
        for (ObjectNode node : objectNode) {
            TestingSite testingSite = new TestingSite(node);
            addTestingSite(testingSite);
        }

    }

    public void printList() {
        for (TestingSite testingSite : testingSites) {
            System.out.println(testingSite);
        }
    }

    /**
     * Get the testing site by suburb name / type of facility (such as Drive Through, Walk-in, Clinics, GPs or Hospitals)
     *
     * @param filter <String, String> where the key is the name of the field(user want to filter by which field) and the value is the value of the field(user's input)
     * @return filteredList,  an array list of testing sites that meets the filter
     */
    public ArrayList<TestingSite> filterBy(HashMap<String, String> filter) {
        ArrayList<TestingSite> filteredList = new ArrayList<>();
        for (TestingSite testingSite : testingSites) {
            //TODO: jayden
        }
        return filteredList;
    }




    private void getSiteByName(String name) {
//        for (ObjectNode node : jsonNodes) {
//            if (node.get("name").asText().equals(name)) {
//
//            }
//        }

    }
}

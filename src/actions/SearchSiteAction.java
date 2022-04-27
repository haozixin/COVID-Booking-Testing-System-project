package actions;

import actors.Actor;
import engine.DataCollection;
import engine.actions.Action;
import testingSites.Location;
import testingSites.SitesCollection;
import testingSites.TestingSite;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * This class is used to search for a testing site.
 */
public class SearchSiteAction extends Action {
    private DataCollection sitesCollection;
    private ArrayList<HashMap<String, String>> factors;

    /**
     * Constructor
     */
    public SearchSiteAction() {
        sitesCollection = SitesCollection.getInstance();
        factors = SitesCollection.factors;
    }

    @Override
    public String execute(Actor actor) throws IOException, InterruptedException {
        // give user a hit
        Scanner s = new Scanner(System.in);
        String value;
        ArrayList<TestingSite> temp1 = new ArrayList<>();
        ArrayList<TestingSite> temp2 = new ArrayList<>();

        for (int i = 0; i < SitesCollection.factors.size(); i++) {
            HashMap<String, String> iterator = factors.get(i);
            if (iterator.containsValue(Location.SUBURB_FIELD)){

                System.out.print("Enter the value of "+Location.SUBURB_FIELD+" by reference " + factors.get(i).keySet()+ " : ");
                value = s.nextLine().trim();
                temp1.addAll(filterBySuburb(value));
            }
            if (iterator.containsValue(TestingSite.FACILITY_TYPE_FIELD)){
                System.out.print("Enter the value of "+TestingSite.FACILITY_TYPE_FIELD+" by reference " + factors.get(i).keySet()+ " : ");
                value = s.nextLine().trim();
                temp2.addAll(filterByType(value));
            }
        }

        for (TestingSite site : temp1) {
            for(TestingSite site2 : temp2){
                if (site.equals(site2)){
                    site.display();
                }
            }
        }

        return null;
    }

    /**
     * filter by suburb
     * @param value  the suburb
     * @return the list of sites
     */
    private ArrayList<TestingSite> filterBySuburb(String value){
        return sitesCollection.filterByOnFactor(Location.SUBURB_FIELD, value);
    }

    /**
     * filter by testing site type
     * @param value the type
     * @return the list of sites
     */
    private ArrayList<TestingSite> filterByType(String value){
        return sitesCollection.filterByOnFactor(TestingSite.FACILITY_TYPE_FIELD, value);
    }

    @Override
    public String menuDescription(Actor actor) {
        return "Search for testing sites";
    }
}

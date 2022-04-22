package actions;

import actors.Actor;
import engine.actions.Action;
import testingSite.Location;
import testingSite.SitesCollection;
import testingSite.TestingSite;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class SearchSiteAction extends Action {
    private SitesCollection sitesCollection;

    public SearchSiteAction() {
        sitesCollection = SitesCollection.getInstance();
    }

    @Override
    public String execute(Actor actor) throws IOException, InterruptedException {
        // give user a hit
        Scanner s = new Scanner(System.in);
        String value;
        ArrayList<TestingSite> temp1 = new ArrayList<>();
        ArrayList<TestingSite> temp2 = new ArrayList<>();

        for (int i = 0; i < sitesCollection.getFactors().size(); i++) {
            HashMap<String, String> iterator = sitesCollection.getFactors().get(i);
            if (iterator.containsValue(Location.SUBURB_FIELD)){

                System.out.print("Enter the value of "+Location.SUBURB_FIELD+" by reference " + sitesCollection.getFactors().get(i).keySet()+ " : ");
                value = s.nextLine().trim();
                temp1.addAll(filterBySuburb(value));
            }
            if (iterator.containsValue(TestingSite.FACILITY_TYPE_FIELD)){
                System.out.print("Enter the value of "+TestingSite.FACILITY_TYPE_FIELD+" by reference " + sitesCollection.getFactors().get(i).keySet()+ " : ");
                value = s.nextLine().trim();
                temp2.addAll(filterByType(value));
            }
        }

        for (TestingSite site : temp1) {
            for(TestingSite site2 : temp2){
                if (site.equals(site2)){
                    System.out.println(site);
                }
            }
        }

        return null;
    }

    private ArrayList<TestingSite> filterBySuburb(String value){
        return sitesCollection.filter(Location.SUBURB_FIELD, value);
    }

    private ArrayList<TestingSite> filterByType(String value){
        return sitesCollection.filter(TestingSite.FACILITY_TYPE_FIELD, value);
    }

    @Override
    public String menuDescription(Actor actor) {
        return "Search for testing sites";
    }
}

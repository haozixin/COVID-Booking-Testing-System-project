package controllers;

import com.fasterxml.jackson.databind.node.ObjectNode;
import enums.Path;
import models.CollectionModel;
import models.facilities.CovidTestingSiteModel;
import views.SearchSitesView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Controller for the SearchSitesView.
 */
public class SearchSiteController extends Controller {
    private SearchSitesView view;
    private CollectionModel dataModel;

    public SearchSiteController(SearchSitesView view, CollectionModel dataModel) {
        this.view = view;
        this.dataModel = dataModel;
        this.view.addButtonListener(new filterListener());
    }

    class filterListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String suburbValue = view.getSuburb();
            String facilityTypeValue = view.getFacilityType();


            ArrayList<ObjectNode> tempFilteredData1 = new ArrayList<>();
            ArrayList<ObjectNode> tempFilteredData2 = new ArrayList<>();
            tempFilteredData1 = dataModel.filterByOnFactor(CovidTestingSiteModel.SUBURB_FIELD,suburbValue);
            tempFilteredData2 = dataModel.filterByOnFactor(CovidTestingSiteModel.FACILITY_TYPE_FIELD,facilityTypeValue);
            ArrayList<ObjectNode> finalFilteredData = new ArrayList<>();
            for (ObjectNode site : tempFilteredData1) {
                for(ObjectNode site2 : tempFilteredData2){
                    if (site.equals(site2)){
                        finalFilteredData.add(site);
                    }
                }
            }
            dataModel.setCollection(finalFilteredData);

            view.update();
            dataModel.updateCollection(Path.SITE.getPath());

        }
    }
}
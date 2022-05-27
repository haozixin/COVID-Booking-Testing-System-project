package views;

import models.CollectionModel;
import models.facilities.CovidTestingSiteModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

/**
 * The view about the SearchSitesService
 */
public class SearchSitesView extends View {
    CollectionModel collectionModel;

    JPanel p1 = new JPanel();
    JPanel p2 = new JPanel();

    JTextArea sites = new JTextArea("Here is the place to list testing sites");
    JScrollPane jp = new JScrollPane(sites);
    JButton searchButton = new JButton("Search");
    JLabel searchLabel1 = new JLabel("Filter by Suburb:");
    JLabel searchLabel2 = new JLabel("Filter by facility type:");
    JComboBox<String> suburbComboBox = new JComboBox<>();
    JComboBox<String> facilityComboBox = new JComboBox<>();


    public SearchSitesView(CollectionModel collectionModel) throws HeadlessException {
        super("Search Sites Subsystem - search for sites");
        this.collectionModel = collectionModel;

        GridBagConstraints c = setBasicStyle(panel);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(900,500);
        sites.setEditable(false);
        jp.setLayout(new ScrollPaneLayout());
        jp.setPreferredSize(new Dimension(800,300));
        sites.setSize(800,300);


        GridBagConstraints c1 = setBasicStyle(p1);
        GridBagConstraints c2 = setBasicStyle(p2);


        setCheckBox();

        c1.gridx = 0;
        c1.gridy = 0;
        p1.add(searchLabel1, c1);

        c1.gridx = 1;
        c1.gridy = 0;
        p1.add(suburbComboBox, c1);

        c1.gridx = 2;
        c1.gridy = 0;
        p1.add(searchLabel2, c1);

        c1.gridx = 3;
        c1.gridy = 0;
        p1.add(facilityComboBox, c1);

        c1.gridx = 4;
        c1.gridy = 0;
        p1.add(searchButton, c1);

        c2.gridx = 0;
        c2.gridy = 0;
        p2.add(jp, c);

        c.gridx = 0;
        c.gridy = 0;
        panel.add(p1, c);

        c.gridx = 0;
        c.gridy = 1;
        panel.add(p2, c);

        add(panel);
    }

    /**
     * Set the checkbox for the search view based on the model's value
     */
    private void setCheckBox(){
        ArrayList<String> filterFactors = new ArrayList<>();
        // initial the factor value that used to filter the sites
        filterFactors.add(CovidTestingSiteModel.SUBURB_FIELD);
        filterFactors.add(CovidTestingSiteModel.FACILITY_TYPE_FIELD);
        // go through all values of entity, find out distinguished values as filters
        collectionModel.initFilterFields(filterFactors);
        ArrayList<HashMap<String, String>> factors = collectionModel.getFactors();

        // add the distinct values to the combo box
        for (HashMap<String, String> factor : factors) {
            for (String key : factor.keySet()) {
                if (factor.get(key).equals(CovidTestingSiteModel.SUBURB_FIELD)) {
                    suburbComboBox.addItem(key);
                }
                if(factor.get(key).equals(CovidTestingSiteModel.FACILITY_TYPE_FIELD)){
                    facilityComboBox.addItem(key);
                }
            }
        }

    }

    @Override
    public void update() {
        sites.setText(collectionModel.display());
    }

    @Override
    public void addButtonListener(ActionListener listener) {
        searchButton.addActionListener(listener);
    }


    public String getSuburb(){
        return Objects.requireNonNull(suburbComboBox.getSelectedItem()).toString();
    }

    public String getFacilityType(){
        return Objects.requireNonNull(facilityComboBox.getSelectedItem()).toString();
    }
}

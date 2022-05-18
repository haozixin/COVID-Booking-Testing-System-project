package views;

import models.CollectionModel;
import models.OnsiteBookingModel;
import models.CovidTestingSiteModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 *
 */
public class OnSiteBookingView extends View {
    private CollectionModel collectionModel;
    private OnsiteBookingModel onsiteBookingModel;
    private CovidTestingSiteModel covidTestingSiteModel;



    JPanel p2 = new JPanel();
    JTextArea sites = new JTextArea("No sites available");
    JScrollPane jp = new JScrollPane(sites);
    JScrollPane outsideJp = new JScrollPane(panel);
    JLabel label = new JLabel("Available Sites(provide on site booking):");
    JLabel siteLabel = new JLabel("Site Id:");
    JTextField siteId = new JTextField(30);
    JLabel userNameLabel = new JLabel("Customer userName:");
    JTextField userNameField = new JTextField(30);
    JButton submitButton = new JButton("Submit");



    public OnSiteBookingView(CollectionModel collectionModel, OnsiteBookingModel onsiteBookingModel, CovidTestingSiteModel covidTestingSiteModel) throws HeadlessException {
        super("On-Site Booking Subsystem - make an on-site booking");
        this.onsiteBookingModel = onsiteBookingModel;
        this.covidTestingSiteModel = covidTestingSiteModel;
        this.collectionModel = collectionModel;

        GridBagConstraints c = setBasicStyle(panel);
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setSize(900,500);
        sites.setEditable(false);
        jp.setLayout(new ScrollPaneLayout());
        jp.setPreferredSize(new Dimension(800,300));
        outsideJp.setLayout(new ScrollPaneLayout());
        outsideJp.setPreferredSize(new Dimension(900,500));
        sites.setSize(800,300);
        sites.setText(this.collectionModel.display());

        GridBagConstraints c2 = setBasicStyle(p2);


        addComponentsToPanel(p2, c2, label);
        addComponentsToPanel(p2, c2, jp);
        addComponentsToPanel(panel, c2, p2);
        addComponentsToPanel(panel, c, siteLabel);
        addComponentsToPanel(panel, c, siteId);
        addComponentsToPanel(panel, c, userNameLabel);
        addComponentsToPanel(panel, c, userNameField);
        addComponentsToPanel(panel, c, submitButton);





        add(outsideJp);

    }

    @Override
    public void update() {
        String error = onsiteBookingModel.getResponseMessage();
        String error2 = covidTestingSiteModel.getResponseMessage();
        if (error.equals("") && error2.equals("")) {
            // if no error, display the sites
            panel.removeAll();

            panel.add(new JLabel("You have successfully made an on-site booking (you could close the window now)"));
            panel.update(panel.getGraphics());
            JOptionPane.showMessageDialog(this, "The testing site's waiting time becomes: " + covidTestingSiteModel.getWaitingTime()+" min ");
        }
        else{
            JOptionPane.showMessageDialog(this, error + "\n" + error2);
        }
    }

    public String getUserNameField() {
        return userNameField.getText();
    }

    public String getSiteId() {
        return siteId.getText();
    }

    @Override
    public void addButtonListener(ActionListener listener) {
        submitButton.addActionListener(listener);
    }
}

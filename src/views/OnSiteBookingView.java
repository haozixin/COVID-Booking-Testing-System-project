package views;

import models.Collection;
import models.OnsiteBooking;
import models.CovidTestingSite;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class OnSiteBookingView extends View {
    private Collection collectionModel;
    private OnsiteBooking onsiteBookingModel;
    private CovidTestingSite covidTestingSiteModel;



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



    public OnSiteBookingView(Collection collection,OnsiteBooking onsiteBookingModel, CovidTestingSite covidTestingSiteModel) throws HeadlessException {
        super("On-Site Booking Subsystem - make an on-site booking");
        this.onsiteBookingModel = onsiteBookingModel;
        this.covidTestingSiteModel = covidTestingSiteModel;
        this.collectionModel = collection;

        GridBagConstraints c = setBasicStyle(panel);
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setSize(900,500);
        sites.setEditable(false);
        jp.setLayout(new ScrollPaneLayout());
        jp.setPreferredSize(new Dimension(800,300));
        outsideJp.setLayout(new ScrollPaneLayout());
        outsideJp.setPreferredSize(new Dimension(900,500));
        sites.setSize(800,300);
        sites.setText(collectionModel.display());

        GridBagConstraints c2 = setBasicStyle(p2);

        c2.gridx = 0;
        c2.gridy = 0;
        p2.add(label, c2);

        c2.gridx = 0;
        c2.gridy = 1;
        p2.add(jp, c2);

        c.gridx = 0;
        c.gridy = 1;
        panel.add(p2, c);

        c.gridx = 0;
        c.gridy = 2;
        panel.add(siteLabel, c);

        c.gridx = 0;
        c.gridy = 3;
        panel.add(siteId, c);

        c.gridx = 0;
        c.gridy = 4;
        panel.add(userNameLabel, c);

        c.gridx = 0;
        c.gridy = 5;
        panel.add(userNameField, c);

        c.gridx = 0;
        c.gridy = 6;
        panel.add(submitButton, c);




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

package views;

import models.bookings.BookingModel;
import models.CollectionModel;
import models.facilities.CovidTestingSiteModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * The view about the OnsiteBookingService
 */
public class OnSiteBookingView extends BookingView {
    private CollectionModel collectionModel;
    private BookingModel onsiteBookingModel;
    private CovidTestingSiteModel covidTestingSiteModel;



    JPanel p2 = new JPanel();
    JTextArea sites = new JTextArea("No sites available");
    JScrollPane jp = new JScrollPane(sites);
    JLabel label = new JLabel("Available Sites(provide on site booking):");
    JLabel siteLabel = new JLabel("Site Id:");
    JTextField siteId = new JTextField(30);
    JLabel userNameLabel = new JLabel("Customer userName:");
    JTextField userNameField = new JTextField(30);


    JButton submitButton = new JButton("Submit");



    public OnSiteBookingView(CollectionModel collectionModel, BookingModel onsiteBookingModel, CovidTestingSiteModel covidTestingSiteModel) throws HeadlessException {
        super("On-Site Booking Subsystem - make an on-site booking");
        this.onsiteBookingModel = onsiteBookingModel;
        this.covidTestingSiteModel = covidTestingSiteModel;
        this.collectionModel = collectionModel;

        GridBagConstraints c = setBasicStyle(panel);

        sites.setEditable(false);
        jp.setLayout(new ScrollPaneLayout());
        jp.setPreferredSize(new Dimension(800,300));
        sites.setSize(800,300);
        sites.setText(this.collectionModel.display());

        setDateTime();

        GridBagConstraints c2 = setBasicStyle(p2);


        addComponentsInY(p2, c2, label);
        addComponentsInY(p2, c2, jp);
        addComponentsInY(panel, c2, p2);
        addComponentsInY(panel, c, siteLabel);
        addComponentsInY(panel, c, siteId);
        addComponentsInY(panel, c, userNameLabel);
        addComponentsInY(panel, c, userNameField);
        addComponentsInY(panel, c, dateLabel);
        addComponentsInY(panel, c, dateField);
        addComponentsInY(panel, c, timeLabel);
        addComponentsInY(panel, c, timeField);


        addComponentsInY(panel, c, submitButton);





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

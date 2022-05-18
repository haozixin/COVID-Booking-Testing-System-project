package views;

import models.CollectionModel;
import models.HomeBookingModel;

import javax.swing.*;
import javax.swing.text.DateFormatter;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Objects;

public class HomeBookingView extends BookingView{
    public static final String HAS_RAT_KIT = "Yes";
    CollectionModel collectionModel;
    private HomeBookingModel homeBookingModel;

    JLabel siteLabel = new JLabel("Site information: ");
    JTextArea sites = new JTextArea("Here is the place to list testing sites");
    JScrollPane jp = new JScrollPane(sites);
    JScrollPane outsideJp = new JScrollPane(panel);
    JLabel siteIdLabel = new JLabel("Testing Site ID:");
    JTextField siteId = new JTextField(30);
    JLabel hasRATKit = new JLabel("Do you already have a RAT kit?");
    JComboBox<String> comboBox = new JComboBox<>();
    JButton submit = new JButton("Submit");



    public HomeBookingView(CollectionModel collectionModel, HomeBookingModel homeBookingModel) throws HeadlessException {
        super("Home Booking Subsystem - book a home testing");
        this.collectionModel = collectionModel;
        this.homeBookingModel = homeBookingModel;

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

        setDateTime();

        comboBox.addItem("No");
        comboBox.addItem(HAS_RAT_KIT);


        addComponentsToPanel(panel, c, siteLabel);
        addComponentsToPanel(panel, c, jp);
        addComponentsToPanel(panel, c, hasRATKit);
        addComponentsToPanel(panel, c, comboBox);
        addComponentsToPanel(panel, c, siteIdLabel);
        addComponentsToPanel(panel, c, siteId);

        addComponentsToPanel(panel, c, dateLabel);
        addComponentsToPanel(panel, c, dateField);
        addComponentsToPanel(panel, c, timeLabel);
        addComponentsToPanel(panel, c, timeField);

        addComponentsToPanel(panel, c, submit);




        add(outsideJp);


    }

    public String getSelectedItem() {
        return Objects.requireNonNull(comboBox.getSelectedItem()).toString();
    }

    public String getSiteId() {
        return siteId.getText();
    }

    @Override
    public void update() {
        if(homeBookingModel.getResponseMessage().equals("")){
            panel.removeAll();
            state.setText("You have successfully booked a home testing");
            panel.add(state);

            JOptionPane.showMessageDialog(this, "We have messaged the url ("+homeBookingModel.getURL()+") to your phone");
            JOptionPane.showMessageDialog(this, "Here is your QR code: "+homeBookingModel.getQRCode());
            panel.update(panel.getGraphics());
            outsideJp.update(outsideJp.getGraphics());
            this.update(this.getGraphics());
        }
        else{
            JOptionPane.showMessageDialog(this, homeBookingModel.getResponseMessage());
        }
    }


    @Override
    public void addButtonListener(ActionListener listener) {
        submit.addActionListener(listener);
    }
}

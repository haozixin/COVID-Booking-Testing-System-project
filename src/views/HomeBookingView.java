package views;

import models.CollectionModel;
import models.HomeBookingModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Objects;

public class HomeBookingView extends View{
    public static final String HAS_RAT_KIT = "Yes";
    CollectionModel collectionModel;
    private HomeBookingModel homeBookingModel;


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



        comboBox.addItem("No");
        comboBox.addItem(HAS_RAT_KIT);

        c.gridx = 0;
        c.gridy = 0;
        panel.add(jp, c);

        c.gridx = 0;
        c.gridy = 1;
        panel.add(hasRATKit, c);

        c.gridx = 0;
        c.gridy = 2;
        panel.add(comboBox, c);

        c.gridx = 0;
        c.gridy = 3;
        panel.add(siteIdLabel, c);

        c.gridx = 0;
        c.gridy = 4;
        panel.add(siteId, c);

        c.gridx = 0;
        c.gridy = 5;
        panel.add(submit, c);



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

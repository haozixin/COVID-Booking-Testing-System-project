package views;

import models.OnsiteBookingModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ChangeBookingView extends BookingView {
    private OnsiteBookingModel onsiteBookingModel;
    JScrollPane outsideJp = new JScrollPane(panel);

    JLabel bookingIdLbl = new JLabel("Booking ID: ");
    JTextField bookingIdTxt = new JTextField(30);

    JLabel siteIdLbl = new JLabel("New venue (enter Site ID): ");
    JTextField siteIdTxt = new JTextField(30);

    JButton button = new JButton("Change Booking");





    public ChangeBookingView(OnsiteBookingModel onsiteBookingModel) throws HeadlessException {
        super("On-Site booking subsystem - Change booking");
        this.onsiteBookingModel = onsiteBookingModel;

        GridBagConstraints c = setBasicStyle(panel);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(900,500);
        outsideJp.setLayout(new ScrollPaneLayout());
        outsideJp.setPreferredSize(new Dimension(900,500));

        setDateTime();

        addComponentsToPanel(panel, c, bookingIdLbl);
        addComponentsToPanel(panel, c, bookingIdTxt);
        addComponentsToPanel(panel, c, siteIdLbl);
        addComponentsToPanel(panel, c, siteIdTxt);
        addComponentsToPanel(panel, c, dateLabel);
        addComponentsToPanel(panel, c, dateField);
        addComponentsToPanel(panel, c, timeLabel);
        addComponentsToPanel(panel, c, timeField);
        addComponentsToPanel(panel, c, button);



        add(outsideJp);
    }

    @Override
    public void update() {
        if (onsiteBookingModel.getResponseMessage().equals("")) {
            JOptionPane.showMessageDialog(this, "Booking successfully changed(you could close this window now)");
        }else{
            JOptionPane.showMessageDialog(this, onsiteBookingModel.getResponseMessage());
        }
    }

    public String getBookingId() {
        return bookingIdTxt.getText();
    }

    public String getSiteId() {
        return siteIdTxt.getText();
    }

    @Override
    public void addButtonListener(ActionListener listener) {
        button.addActionListener(listener);
    }
}

package views;

import models.CollectionModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class CheckBookingView extends View {
    private CollectionModel collectionModel;

    JPanel p1 = new JPanel();
    JPanel p2 = new JPanel();
    JTextArea bookings = new JTextArea("Here is the place to list booking information");
    JScrollPane jp = new JScrollPane(bookings);
    JButton checkButton = new JButton("Check Booking");
    JLabel pinLabel = new JLabel("Pin Code:");
    JTextField pinField = new JTextField(10);

    public CheckBookingView(CollectionModel collectionModel) throws HeadlessException {
        super("Onsite-Booking Subsystem");
        this.collectionModel = collectionModel;

        GridBagConstraints c = setBasicStyle(panel);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(900,500);
        bookings.setEditable(false);
        jp.setLayout(new ScrollPaneLayout());
        jp.setPreferredSize(new Dimension(800,300));
        bookings.setSize(800,300);

        GridBagConstraints c1 = setBasicStyle(p1);
        GridBagConstraints c2 = setBasicStyle(p2);

        c1.gridx = 0;
        c1.gridy = 0;
        p1.add(pinLabel, c1);

        c1.gridx = 1;
        c1.gridy = 0;
        p1.add(pinField, c1);

        c1.gridx = 2;
        c1.gridy = 0;
        p1.add(checkButton, c1);

        c2.gridx = 0;
        c2.gridy = 0;
        p2.add(jp, c2);

        c.gridx = 0;
        c.gridy = 0;
        panel.add(p1, c);

        c.gridx = 0;
        c.gridy = 1;
        panel.add(p2, c);

        add(panel);

    }

    @Override
    public void update() {
        bookings.setText(collectionModel.display());
    }

    public String getPinCode() {
        return pinField.getText();
    }

    @Override
    public void addButtonListener(ActionListener listener) {
        checkButton.addActionListener(listener);
    }
}

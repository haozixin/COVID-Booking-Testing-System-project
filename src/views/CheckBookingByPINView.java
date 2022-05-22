package views;

import models.bookings.BookingModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class CheckBookingByPINView extends View {
    private BookingModel bookingModel;

    JPanel p1 = new JPanel();
    JPanel p2 = new JPanel();
    JTextArea booking = new JTextArea("Here is the place to list booking information");
    JScrollPane jp = new JScrollPane(booking);

    JLabel pinLabel = new JLabel("Pin Code:");
    JTextField pinField = new JTextField(20);
    JLabel bookingLabel = new JLabel("Booking ID:");
    JTextField bookingField = new JTextField(20);
    JButton checkButton = new JButton("Check Booking");

    public CheckBookingByPINView(BookingModel bookingModel) throws HeadlessException {
        super("Onsite-Booking Subsystem");
        this.bookingModel = bookingModel;

        GridBagConstraints c = setBasicStyle(panel);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(900,500);
        booking.setEditable(false);
        jp.setLayout(new ScrollPaneLayout());
        jp.setPreferredSize(new Dimension(800,300));
        booking.setSize(800,300);

        GridBagConstraints c1 = setBasicStyle(p1);
        GridBagConstraints c2 = setBasicStyle(p2);


        addComponentsInX(p1, c1, pinLabel);
        addComponentsInX(p1, c1, pinField);
        addComponentsInX(p1, c1, bookingLabel);
        addComponentsInX(p1, c1, bookingField);
        addComponentsInX(p1, c1, checkButton);



        addComponentsInY(p2, c2, jp);


        addComponentsInY(panel, c, p1);
        addComponentsInY(panel, c, p2);


        add(panel);

    }

    @Override
    public void update() {

        booking.setText(bookingModel.display());
        panel.update(panel.getGraphics());

    }

    public String getBookingId() {
        return bookingField.getText();
    }

    public String getPinCode() {
        return pinField.getText();
    }

    @Override
    public void addButtonListener(ActionListener listener) {
        checkButton.addActionListener(listener);
    }
}

package views;

import models.bookings.HomeBookingModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * The view about the ScanQRCodeService
 */
public class ScanQRView extends View{
    private HomeBookingModel bookingModel;
    JLabel note = new JLabel("Assumption: Input QR code to simulate scanning QR code");
    JLabel label = new JLabel("Scan QR code:");
    JTextField textField = new JTextField(30);
    JButton button = new JButton("Scan");


    public ScanQRView(HomeBookingModel bookingModel) throws HeadlessException {
        super("Home Booking Subsystem - Scan QR");
        this.bookingModel = bookingModel;

        GridBagConstraints c = setBasicStyle(panel);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(900,400);


        addComponentsInY(panel, c, note);
        addComponentsInY(panel, c, label);
        addComponentsInY(panel, c, textField);
        addComponentsInY(panel, c, button);


        add(panel);


    }

    public String getQRCode() {
        return textField.getText();
    }

    @Override
    public void update() {
        if (bookingModel.isUpdated()) {
            panel.removeAll();
            note.setText("Scanning QR code is successful and has changed the hasRATKit value to true(in Web Server)");
            note.setSize(800,100);

            panel.add(note);

            panel.update(panel.getGraphics());
            // set the updated flag back to false, so that it won't update again, and we can reuse the attribute if somewhere isUpdated() is called
            bookingModel.setIsUpdated(false);
        }
        else{
            JOptionPane.showMessageDialog(this,"It's not a valid QR code");
        }


    }

    @Override
    public void addButtonListener(ActionListener listener) {
        button.addActionListener(listener);
    }
}

package views;

import engine.CurrentOperator;
import engine.adminNotification.BookingPublisher;
import engine.adminNotification.Publisher;
import models.bookings.BookingModel;
import models.users.UserModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

public class ProfileView extends View {
    UserModel userModel;
    BookingModel onsiteBookingModel;
    JScrollPane outsideJp = new JScrollPane(panel);


    private JLabel idLabel = new JLabel("ID: ");
    private JLabel givenNameLabel = new JLabel("Given Name: ");
    private JLabel familyNameLabel = new JLabel("Family Name: ");
    private JLabel userNameLabel = new JLabel("Username: ");
    private JLabel phoneNumberLabel = new JLabel("Phone Number: ");
    private JLabel bookingsLabel = new JLabel("On-site Bookings: ");
    private HashMap<JButton, BookingModel> buttons = new HashMap<>();
//    private JLabel bookingIdLabel = new JLabel("Booking ID: ");
//    private JTextField bookingIdTextField = new JTextField(30);
//    private JButton cancelBookingButton = new JButton("Cancel the Booking");


    public ProfileView(UserModel userModel, BookingModel onsiteBookingModel) throws HeadlessException {
        super("On-site booking subsystem - Profile ");
        GridBagConstraints c = setBasicStyle(panel);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(700, 400);
        outsideJp.setLayout(new ScrollPaneLayout());
        this.userModel = userModel;
        this.onsiteBookingModel = onsiteBookingModel;


        idLabel.setText(idLabel.getText().concat(userModel.getId()));
        givenNameLabel.setText(givenNameLabel.getText().concat(userModel.getGivenName()));
        familyNameLabel.setText(familyNameLabel.getText().concat(userModel.getFamilyName()));
        userNameLabel.setText(userNameLabel.getText().concat(userModel.getUserName()));
        phoneNumberLabel.setText(phoneNumberLabel.getText().concat(userModel.getPhoneNumber()));


        addComponentsInY(panel, c, idLabel);
        addComponentsInY(panel, c, givenNameLabel);
        addComponentsInY(panel, c, familyNameLabel);
        addComponentsInY(panel, c, userNameLabel);
        addComponentsInY(panel, c, phoneNumberLabel);
        addComponentsInY(panel, c, bookingsLabel);
        addComponentsInY(panel, c, new JLabel("-------------------Your bookings are below---------------------"));
        ArrayList<BookingModel> bookings = userModel.getOnSiteBookings();
        for (BookingModel booking : bookings) {
            addBooking(c, booking);
        }


        add(outsideJp);
    }

    private void addBooking(GridBagConstraints c, BookingModel booking) {

        JTextArea bookingTextArea = new JTextArea(booking.display());
        JScrollPane jp = new JScrollPane(bookingTextArea);
        jp.setLayout(new ScrollPaneLayout());
        bookingTextArea.setEditable(false);
        bookingTextArea.setSize(600, 300);
        jp.setSize(600, 300);
        addComponentsInY(panel, c, jp);
        JButton changeButton = new JButton("Change the Booking");
        JButton cancelButton = new JButton("Cancel the Booking");
        cancelButton.addActionListener(e -> {
                    booking.cancelBooking();
                    String facilityId = booking.getVenueId();
                    // Broadcast new message to all subscribers (within a range - for example, only subscribers within the same facility)
                    Publisher publisher = BookingPublisher.getInstance();
                    CurrentOperator.getInstance().broadCast(publisher, "a booking was canceled.", facilityId);

                    if (booking.getResponseMessage().equals("")) {
                        JOptionPane.showMessageDialog(this, "Booking cancelled successfully");
                    } else {
                        JOptionPane.showMessageDialog(this, booking.getResponseMessage());
                    }

                }

        );

        addComponentsInY(panel, c, cancelButton);
        addComponentsInY(panel, c, changeButton);
        addComponentsInY(panel, c, new JLabel("----------------------------------------------------------------"));
        // add buttons into an arrayList

        buttons.put(changeButton, booking);

    }


    @Override
    public void update() {


    }

    @Override
    public void addButtonListener(ActionListener listener) {
        // add the same listener to all buttons
        for (JButton button : buttons.keySet()) {
            button.addActionListener(listener);
        }
    }
}

package views;

import engine.CurrentOperator;
import models.CollectionModel;
import models.OnsiteBookingModel;
import models.UserModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ProfileView extends View{
    UserModel userModel;
    OnsiteBookingModel onsiteBookingModel;
    JScrollPane outsideJp = new JScrollPane(panel);


    private JLabel idLabel = new JLabel("ID: ");
    private JLabel givenNameLabel = new JLabel("Given Name: ");
    private JLabel familyNameLabel = new JLabel("Family Name: ");
    private JLabel userNameLabel = new JLabel("Username: ");
    private JLabel phoneNumberLabel = new JLabel("Phone Number: ");
    private JLabel bookingsLabel = new JLabel("On-site Bookings: ");
    private ArrayList<JButton> buttons = new ArrayList<>();



    public ProfileView(UserModel userModel, OnsiteBookingModel onsiteBookingModel) throws HeadlessException {
        super("On-site booking subsystem - Profile ");
        GridBagConstraints c = setBasicStyle(panel);
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setSize(600,400);
        outsideJp.setLayout(new ScrollPaneLayout());
        this.userModel = userModel;
        this.onsiteBookingModel = onsiteBookingModel;


        idLabel.setText(idLabel.getText().concat(userModel.getId()));
        givenNameLabel.setText(givenNameLabel.getText().concat(userModel.getGivenName()));
        familyNameLabel.setText(familyNameLabel.getText().concat(userModel.getFamilyName()));
        userNameLabel.setText(userNameLabel.getText().concat(userModel.getUserName()));
        phoneNumberLabel.setText(phoneNumberLabel.getText().concat(userModel.getPhoneNumber()));

        addComponentsToPanel(panel, c, idLabel);
        addComponentsToPanel(panel, c, givenNameLabel);
        addComponentsToPanel(panel, c, familyNameLabel);
        addComponentsToPanel(panel, c, userNameLabel);
        addComponentsToPanel(panel, c, phoneNumberLabel);
        addComponentsToPanel(panel, c, bookingsLabel);

        ArrayList<OnsiteBookingModel> bookings = userModel.getOnSiteBookings();
        for(OnsiteBookingModel booking : bookings){
            addBooking(c, booking);
        }


        add(outsideJp);
    }

    private void addBooking(GridBagConstraints c, OnsiteBookingModel booking){

        JTextArea bookingTextArea = new JTextArea(booking.display());
        JScrollPane jp = new JScrollPane(bookingTextArea);
        jp.setLayout(new ScrollPaneLayout());
        bookingTextArea.setEditable(false);
        bookingTextArea.setSize(400,300);
        jp.setSize(400,300);
        addComponentsToPanel(panel, c, jp);
        JButton button = new JButton("Change the Booking");
        addComponentsToPanel(panel, c, button);
        // add buttons into an arrayList
        buttons.add(button);
    }




    @Override
    public void update() {


    }

    @Override
    public void addButtonListener(ActionListener listener) {
        // add the same listener to all buttons
    }
}

package views;

import engine.CurrentOperator;
import models.bookings.BookingModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * The view about the AdminBookingService
 */
public class AdminBookingView extends View {
    private BookingModel bookingModel;

    protected JScrollPane outsideJp = new JScrollPane(panel);
    protected JLabel cancelBookingLbl = new JLabel("Canceled Booking:");
    protected JTextArea canceledBookings = new JTextArea("There is no canceled booking");
    protected JScrollPane canceledBookingP = new JScrollPane(canceledBookings);

    protected JLabel modifiedBookingLbl = new JLabel("Modified Booking:");
    protected JTextArea modifiedBookings = new JTextArea("There is no modified booking");
    protected JScrollPane modifiedBookingP = new JScrollPane(modifiedBookings);

    protected JLabel newBookingLbl = new JLabel("New Booking:");
    protected JTextArea newBookings = new JTextArea("There is no booking");
    protected JScrollPane newBookingP = new JScrollPane(newBookings);

    protected JLabel deleteBookingLbl = new JLabel("Booking Id:");
    protected JTextField deleteBooking = new JTextField(30);
    protected JButton deleteBookingBtn = new JButton("Delete the Booking");

    protected JButton mkNewBookingBtn = new JButton("Make a new Booking");
    protected JButton mkModifiedBookingBtn = new JButton("modify Booking");


    public AdminBookingView(BookingModel bookingModel) throws HeadlessException {
        super("Admin Booking Window");
        this.bookingModel = bookingModel;
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(1000,600);
        outsideJp.setLayout(new ScrollPaneLayout());
        outsideJp.setPreferredSize(new Dimension(1000,600));
        GridBagConstraints c = setBasicStyle(panel);

        // retrieve the message published by others
        ArrayList<String> message = CurrentOperator.getInstance().receiveMessage();
        if (!message.isEmpty()){
            for(String s : message){
                System.out.println(s);
                JOptionPane.showMessageDialog(this, s);
            }
        }

        addComponentsInY(panel, c, new JLabel("-------------------View-Bookings Section---------------------"));
        setCanceledBookingP(cancelBookingLbl, canceledBookings, c, canceledBookingP);
        setCanceledBookingP(modifiedBookingLbl, modifiedBookings, c, modifiedBookingP);
        setCanceledBookingP(newBookingLbl, newBookings, c, newBookingP);

        addComponentsInY(panel, c, new JLabel("-------------------Delete-Bookings Section---------------------"));
        addComponentsInY(panel, c, deleteBookingLbl);
        addComponentsInY(panel, c, deleteBooking);
        addComponentsInY(panel, c, deleteBookingBtn);

        addComponentsInY(panel, c, new JLabel("-------------------Modify-Bookings Section---------------------"));
        addComponentsInY(panel, c, mkNewBookingBtn);
        addComponentsInY(panel, c, mkModifiedBookingBtn);


        add(outsideJp);
    }

    private void setCanceledBookingP(JLabel label, JTextArea text, GridBagConstraints c, JScrollPane scrollPane) {
        scrollPane.setLayout(new ScrollPaneLayout());
        scrollPane.setPreferredSize(new Dimension(900,300));
        text.setEditable(false);
        addComponentsInY(panel, c, label);
        addComponentsInY(panel, c, scrollPane);
    }

    @Override
    public void update() {
        if (bookingModel.getResponseMessage().equals("")){
            JOptionPane.showMessageDialog(this, "Have deleted the booking");
        }
        else{
            JOptionPane.showMessageDialog(this, bookingModel.getResponseMessage());
        }
    }
    public void updateView(){
        canceledBookings.setText(bookingModel.getCanceledBookings());
        modifiedBookings.setText(bookingModel.getModifiedBookings(true));
        newBookings.setText(bookingModel.getModifiedBookings(false));
    }

    @Override
    public void addButtonListener(ActionListener listener) {

    }

    public void addDeleteBookingListener(ActionListener listener){
    	deleteBookingBtn.addActionListener(listener);
    }
    public void addNewBookingListener(ActionListener listener){
    	mkNewBookingBtn.addActionListener(listener);
    }
    public void addModifiedBookingListener(ActionListener listener){
    	mkModifiedBookingBtn.addActionListener(listener);
    }

    public String getBookingId() {
        return deleteBooking.getText();
    }
}

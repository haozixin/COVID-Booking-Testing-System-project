package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class AdminBookingView extends View {
    protected JScrollPane outsideJp = new JScrollPane(panel);
    protected JLabel cancelBookingLbl = new JLabel("Canceled Booking:");
    protected JTextField canceledBookings = new JTextField("There is no canceled booking");
    protected JScrollPane canceledBookingP = new JScrollPane(canceledBookings);

    protected JLabel modifiedBookingLbl = new JLabel("Modified Booking:");
    protected JTextField modifiedBookings = new JTextField("There is no modified booking");
    protected JScrollPane modifiedBookingP = new JScrollPane(modifiedBookings);

    protected JLabel newBookingLbl = new JLabel("New Booking:");
    protected JTextField newBookings = new JTextField("There is no booking");
    protected JScrollPane newBookingP = new JScrollPane(newBookings);

    protected JLabel deleteBookingLbl = new JLabel("Booking Id:");
    protected JTextField deleteBooking = new JTextField(30);
    protected JButton deleteBookingBtn = new JButton("Delete the Booking");

    protected JButton mkNewBookingBtn = new JButton("Make a new Booking");
    protected JButton mkModifiedBookingBtn = new JButton("modify Booking");


    public AdminBookingView() throws HeadlessException {
        super("Admin Booking Window");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(1000,600);
        outsideJp.setLayout(new ScrollPaneLayout());
        outsideJp.setPreferredSize(new Dimension(1000,600));
        GridBagConstraints c = setBasicStyle(panel);

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

    private void setCanceledBookingP(JLabel label, JTextField textField, GridBagConstraints c, JScrollPane scrollPane) {
        scrollPane.setLayout(new ScrollPaneLayout());
        scrollPane.setPreferredSize(new Dimension(900,300));
        textField.setEditable(false);
        addComponentsInY(panel, c, label);
        addComponentsInY(panel, c, scrollPane);
    }

    @Override
    public void update() {

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

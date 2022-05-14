package views;

import models.Collection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class HomeBookingView extends View{
    Collection collectionModel;

    JTextArea sites = new JTextArea("Here is the place to list testing sites");
    JScrollPane jp = new JScrollPane(sites);
    JScrollPane outsideJp = new JScrollPane(panel);
    // date time picker





    public HomeBookingView(Collection collectionModel) throws HeadlessException {
        super("Home Booking Subsystem - book a home testing");
        this.collectionModel = collectionModel;

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

        c.gridx = 0;
        c.gridy = 0;
        panel.add(jp, c);



        add(outsideJp);


    }

    @Override
    public void update() {

    }

    @Override
    public void addButtonListener(ActionListener listener) {

    }
}

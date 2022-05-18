package views;

import models.OnsiteBookingModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ChangeBookingView extends View {
    private OnsiteBookingModel onsiteBookingModel;
    JScrollPane outsideJp = new JScrollPane(panel);


    public ChangeBookingView(OnsiteBookingModel onsiteBookingModel) throws HeadlessException {
        super("On-Site booking subsystem - Change booking");
        this.onsiteBookingModel = onsiteBookingModel;



    }

    @Override
    public void update() {

    }

    @Override
    public void addButtonListener(ActionListener listener) {

    }
}

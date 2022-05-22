package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class AdminBookingView extends View {
    protected JScrollPane outsideJp = new JScrollPane(panel);

    public AdminBookingView() throws HeadlessException {
        super("Admin Booking Window");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(900,500);
        outsideJp.setLayout(new ScrollPaneLayout());
        outsideJp.setPreferredSize(new Dimension(900,500));

        GridBagConstraints c = setBasicStyle(panel);

    }

    @Override
    public void update() {

    }

    @Override
    public void addButtonListener(ActionListener listener) {

    }
}

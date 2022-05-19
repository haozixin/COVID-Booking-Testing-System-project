package views;

import mementos.BookingCaretaker;
import mementos.BookingMemento;
import mementos.Caretaker;
import mementos.IMemento;
import models.OnsiteBookingModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

public class ChangeBookingView extends BookingView {
    private OnsiteBookingModel onsiteBookingModel;
    JScrollPane outsideJp = new JScrollPane(panel);

    JLabel bookingIdLbl = new JLabel("Booking ID: ");
    JTextField bookingIdTxt = new JTextField(30);

    JLabel siteIdLbl = new JLabel("New venue (enter Site ID): ");
    JTextField siteIdTxt = new JTextField(30);

    JButton button = new JButton("Change Booking");




    public ChangeBookingView(OnsiteBookingModel onsiteBookingModel) throws HeadlessException {
        super("On-Site booking subsystem - Change booking");
        this.onsiteBookingModel = onsiteBookingModel;

        GridBagConstraints c = setBasicStyle(panel);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(900,500);
        outsideJp.setLayout(new ScrollPaneLayout());
        outsideJp.setPreferredSize(new Dimension(900,500));

        setDateTime();

        addComponentsToPanel(panel, c, bookingIdLbl);
        addComponentsToPanel(panel, c, bookingIdTxt);
        addComponentsToPanel(panel, c, siteIdLbl);
        addComponentsToPanel(panel, c, siteIdTxt);
        addComponentsToPanel(panel, c, dateLabel);
        addComponentsToPanel(panel, c, dateField);
        addComponentsToPanel(panel, c, timeLabel);
        addComponentsToPanel(panel, c, timeField);
        addComponentsToPanel(panel, c, button);
        addComponentsToPanel(panel, c, new JLabel("-------------------You could chose one history bellow to recover it---------------------"));

        Caretaker caretaker = BookingCaretaker.getInstance();
        caretaker.update();
        ArrayList<IMemento> histories = caretaker.getHistories();
        for(IMemento history : histories){
            addHistory(c, history);
        }

        add(outsideJp);
    }

    @Override
    public void update() {
        if (onsiteBookingModel.getResponseMessage().equals("")) {
            JOptionPane.showMessageDialog(this, "Booking successfully changed(see you next time)");
            dispose();
        }else{
            JOptionPane.showMessageDialog(this, onsiteBookingModel.getResponseMessage());
        }
    }

    public String getBookingId() {
        return bookingIdTxt.getText();
    }

    public String getSiteId() {
        return siteIdTxt.getText();
    }

    private void addHistory(GridBagConstraints c, IMemento history){
        JTextArea historyText = new JTextArea(history.getMetaData());
        JScrollPane jp = new JScrollPane(historyText);
        jp.setLayout(new ScrollPaneLayout());
        historyText.setEditable(false);
        addComponentsToPanel(panel, c, jp);
        JButton button = new JButton("RollBack this history");
        button.addActionListener(e -> {
                    history.restore();
                    BookingCaretaker.getInstance().removeOneMemento(history);
                    JOptionPane.showMessageDialog(this, "History successfully rollback");
                    dispose();
                });
        addComponentsToPanel(panel, c, button);

    }

    @Override
    public void addButtonListener(ActionListener listener) {
        button.addActionListener(listener);
    }

//    public void addRollBackListener(ActionListener listener){
//        for(JButton button : RollBacks.keySet()){
//            button.addActionListener(listener);
//        }
//    }
}

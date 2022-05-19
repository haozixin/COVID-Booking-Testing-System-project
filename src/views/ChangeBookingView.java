package views;

import mementos.BookingCaretaker;
import mementos.Caretaker;
import mementos.IMemento;
import models.BookingModel;
import models.OnsiteBookingModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ChangeBookingView extends BookingView {
    private BookingModel onsiteBookingModel;


    JLabel bookingIdLbl = new JLabel("Booking ID: ");
    JTextField bookingIdTxt = new JTextField(30);

    JLabel siteIdLbl = new JLabel("New venue (enter Site ID): ");
    JTextField siteIdTxt = new JTextField(30);

    JButton button = new JButton("Change Booking");




    public ChangeBookingView(BookingModel onsiteBookingModel) throws HeadlessException {
        super("On-Site booking subsystem - Change booking");
        this.onsiteBookingModel = onsiteBookingModel;


        GridBagConstraints c = setBasicStyle(panel);

        setDateTime();

        addComponentsInY(panel, c, bookingIdLbl);
        addComponentsInY(panel, c, bookingIdTxt);
        addComponentsInY(panel, c, siteIdLbl);
        addComponentsInY(panel, c, siteIdTxt);
        addComponentsInY(panel, c, dateLabel);
        addComponentsInY(panel, c, dateField);
        addComponentsInY(panel, c, timeLabel);
        addComponentsInY(panel, c, timeField);
        addComponentsInY(panel, c, button);
        addComponentsInY(panel, c, new JLabel("-------------------You could chose one history bellow to recover it---------------------"));

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
        addComponentsInY(panel, c, jp);
        JButton button = new JButton("RollBack this history");
        button.addActionListener(e -> {
                    history.restore();
                    BookingCaretaker.getInstance().removeOneMemento(history);
                    JOptionPane.showMessageDialog(this, "History successfully rollback");
                    dispose();
                });
        addComponentsInY(panel, c, button);

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

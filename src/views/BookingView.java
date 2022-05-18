package views;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public abstract class BookingView extends View {
    protected JLabel dateLabel = new JLabel("Date : ");
    protected JFormattedTextField dateField;
    protected JLabel timeLabel = new JLabel("Time : ");
    protected JFormattedTextField timeField;

    public BookingView(String title) throws HeadlessException {
        super(title);
    }

    @Override
    public void update() {

    }

    @Override
    public void addButtonListener(ActionListener listener) {

    }

    protected void setDateTime(){

        try {
            MaskFormatter mf = new MaskFormatter("####-##-##");
            mf.setPlaceholder("YYYY-MM-DD");
            dateField = new JFormattedTextField(mf);

            MaskFormatter mf2 = new MaskFormatter("##:##");
            mf2.setPlaceholder("HH:MM");
            timeField = new JFormattedTextField(mf2);

        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
    public String getDate() {
        return dateField.getText();
    }

    public String getTime() {
        return timeField.getText();
    }
}

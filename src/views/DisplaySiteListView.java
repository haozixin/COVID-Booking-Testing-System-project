package views;

import models.Collection;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class DisplaySiteListView extends View {
    private Collection theModel;

    JTextArea text = new JTextArea();
    JScrollPane scrollPane = new JScrollPane(text);


    public DisplaySiteListView(Collection theModel) throws HeadlessException {
        super("Search-for-sites Subsystem - View Site List");

        this.theModel = theModel;
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setBounds(100, 100, 650, 450); //位置（100 ，100）宽：450，高300

        //Border describes the Border around the panel (inside the panel). EmptyBorder is a blank Border of 5;
        text.setBorder(new EmptyBorder(5, 5, 5, 5));
        text.setLayout(new BorderLayout(0, 0));
//        setContentPane(text);

        // add the text area to the panel
        text.setEditable(false);




        this.add(scrollPane);


    }

    @Override
    public void update() {
        String displayList = theModel.display();
        text.setText(displayList);
    }

    @Override
    public void addButtonListener(ActionListener listener) {
    }
}

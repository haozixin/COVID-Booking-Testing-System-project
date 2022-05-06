package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public abstract class View extends JFrame {

    protected JPanel panel = new JPanel();
    public View(String title) throws HeadlessException {
        super(title);
    }

    public abstract void update();

    public abstract void addButtonListener(ActionListener listener);

    protected GridBagConstraints setBasicStyle(int width, int height) {
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setSize(width, height);
        panel.setLayout(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(10, 10, 10, 10);
        return constraints;
    }
}

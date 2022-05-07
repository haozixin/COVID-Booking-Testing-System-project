package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public abstract class View extends JFrame {
    protected JLabel state = new JLabel("");

    protected JPanel panel = new JPanel();
    public View(String title) throws HeadlessException {
        super(title);
        state.setFont(new Font("Arial", Font.BOLD, 14));
        state.setForeground(new Color(	0, 139, 69));
    }

    public abstract void update();
    public void update(boolean result){}

    public abstract void addButtonListener(ActionListener listener);

    protected GridBagConstraints setBasicStyle(JPanel panel, int width, int height) {
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setSize(width, height);
        panel.setLayout(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(10, 10, 10, 10);
        return constraints;
    }

    protected void addOneComponent(GridBagConstraints constraints, int i, JLabel jLabel, JTextField jTextField) {
        constraints.gridx = 0;
        constraints.gridy = i;
        panel.add(jLabel, constraints);
        constraints.gridx = 1;
        constraints.gridy = i;
        panel.add(jTextField, constraints);
    }

    protected JComponent makeTextPanel(String text)
    {
        JPanel panel=new JPanel(false);
        JLabel filler=new JLabel(text);
        filler.setHorizontalAlignment(JLabel.CENTER);
        panel.setLayout(new GridLayout(1,1));
        panel.add(filler);
        return panel;
    }


}

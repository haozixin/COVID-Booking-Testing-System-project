package controllers;

import views.ProfileView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProfileController extends Controller {
    private ProfileView view;

    public ProfileController(ProfileView view) {
        this.view = view;
        view.addButtonListener(new buttonListener());
    }

    class buttonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }
}

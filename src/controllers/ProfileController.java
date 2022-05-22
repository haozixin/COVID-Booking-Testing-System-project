package controllers;

import models.bookings.BookingModel;
import models.bookings.OnsiteBookingModel;
import views.ChangeBookingView;
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
            BookingModel booking = new OnsiteBookingModel();
            ChangeBookingView changeBookingView = new ChangeBookingView(booking);
            Controller controller = new ChangeBookingController(changeBookingView, booking);
            changeBookingView.setVisible(true);
        }
    }
}

package services;

import engine.CurrentOperator;
import engine.Service;
import engine.Services;
import models.OnsiteBookingModel;
import views.ChangeBookingView;

import java.util.List;

/**
 * Action to change the booking.
 */
public class ChangeBookingService extends Service{


    @Override
    public String execute(CurrentOperator currentOperator) {
        OnsiteBookingModel booking = new OnsiteBookingModel();
        ChangeBookingView changeBookingView = new ChangeBookingView(booking);
        changeBookingView.setVisible(true);
        return "";
    }

    @Override
    public String menuDescription(CurrentOperator currentOperator) {
        return "Change my booking(venue and timing)";
    }
}

package services;

import controllers.ChangeBookingController;
import engine.CurrentOperator;
import engine.Service;
import engine.Services;
import models.OnsiteBookingModel;
import views.ChangeBookingView;

import java.util.List;

public class ChangeBookingService extends Service{


    @Override
    public String execute(CurrentOperator currentOperator) {
        OnsiteBookingModel booking = new OnsiteBookingModel();
        ChangeBookingView changeBookingView = new ChangeBookingView(booking);
        controller = new ChangeBookingController(changeBookingView, booking);
        changeBookingView.setVisible(true);
        return "";
    }

    @Override
    public String menuDescription(CurrentOperator currentOperator) {
        return "Change my booking(venue and timing)";
    }
}

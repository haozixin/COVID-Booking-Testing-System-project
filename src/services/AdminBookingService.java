package services;

import engine.CurrentOperator;
import engine.Service;
import engine.Services;
import views.AdminBookingView;

import java.util.List;

public class AdminBookingService extends Service{
    @Override
    public String execute(CurrentOperator currentOperator) {
        AdminBookingView adminBookingView = new AdminBookingView();
        adminBookingView.setVisible(true);
        return "";
    }

    @Override
    public String menuDescription(CurrentOperator currentOperator) {
        return "Admin Booking Interface";
    }
}

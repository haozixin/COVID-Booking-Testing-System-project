package services;

import engine.CurrentOperator;
import engine.Service;
import models.OnsiteBookingModel;
import views.ProfileView;

public class ViewProfileService extends Service {
    @Override
    public String execute(CurrentOperator currentOperator) {
        OnsiteBookingModel onsiteBookingModel = new OnsiteBookingModel();
        ProfileView profileView = new ProfileView(currentOperator.getProfile(), onsiteBookingModel);
        profileView.setVisible(true);
        return "";
    }

    @Override
    public String menuDescription(CurrentOperator currentOperator) {
        return "View my profile";
    }
}

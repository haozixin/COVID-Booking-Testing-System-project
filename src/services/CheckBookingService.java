package services;

import controllers.CheckBookingController;
import engine.Service;
import engine.CurrentOperator;
import models.CollectionModel;
import views.CheckBookingByPINView;

/**
 * Action to check the booking.
 */
public class CheckBookingService extends Service {
    @Override
    public String execute(CurrentOperator currentOperator) {
        CollectionModel collectionModel = new CollectionModel();
        CheckBookingByPINView view = new CheckBookingByPINView(collectionModel);
        controller = new CheckBookingController(view, collectionModel);
        view.setVisible(true);
        return "";
    }

    @Override
    public String menuDescription(CurrentOperator currentOperator) {
        return "Help residents change their booking";
    }
}
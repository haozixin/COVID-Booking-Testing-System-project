package actions;

import actors.Actor;
import bookings.Booking;
import bookings.BookingsCollection;
import engine.DataCollection;
import engine.actions.Action;

import java.io.IOException;
import java.util.Scanner;

public class ScanQRcodeAction extends Action {
    DataCollection bookingsCollection = BookingsCollection.getInstance();

    @Override
    public String execute(Actor actor) throws IOException, InterruptedException {
        // we assume that scanning QR code is a process/operation to get all necessary information and set "hasRATKit" to true
        // As we use UUID to emulate the QR code, so scanning QR code is same as the process/operation of inputting UUID to the system
        System.out.print("PLease input the QRCode(field name) value:");
        Scanner scanner = new Scanner(System.in);
        String uuid = scanner.nextLine();
        boolean isSuccessful = bookingsCollection.updateHasRATKit(uuid, true);
        if (isSuccessful) {
            return "Scanning QR code is successful and changed the hasRATKit value to true";
        }

        return "It's not a valid QR code";
    }

    @Override
    public String menuDescription(Actor actor) {
        return "Scan QR code";
    }
}

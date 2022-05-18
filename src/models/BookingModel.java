package models;

import com.fasterxml.jackson.databind.node.ObjectNode;
import enums.Path;
import enums.Query;
import mementos.BookingMemento;
import mementos.IOriginator;
import utility.Utility;
import webServiceAPI.ServicesAdapter;
import webServiceAPI.WebServicesTarget;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 *
 */
public abstract class BookingModel extends EntityModel implements IOriginator {


    public static final String USER_ID_FIELD = "customerId";
    public static final String TESTING_SITE_ID_FIELD = "testingSiteId";
    public static final String BOOKING_TYPE_FIELD = "bookingType";

    public static final String SMS_PIN_FIELD = "smsPin";
    public static final String START_TIME_FIELD = "startTime";
    public static final String ADDITIONAL_INFO_FIELD = "additionalInfo";
    public static final String QR_CODE_FIELD = "QRCode";
    public static final String URL_FIELD = "url";
    public static final String HAS_RAT_KIT_FIELD = "hasRATKit";
    public static final String ID_FIELD = "id";
    public static final String STATUS_FIELD = "status";
    private static final String CANCELED_STATUS = "Canceled";

    public BookingModel(){

    }

    public BookingModel(ObjectNode data) {
        updateModel(data);
    }


    protected String generateQRCode() {
        //Unique QR Code ( We assume that the QR code will be generated by the third party system)
        // here we just use UUID in java to emulate the QR code
        String uuid = UUID.randomUUID().toString();

        return uuid;
    }

    protected String generateURL() {
        // Unique url ( We assume that the QR code will be generated by the third party system)
        //  here we just use UUID in java to emulate the QR code
        String uuid = UUID.randomUUID().toString();
        String url = "https://Dummy URL/... - " + uuid;
        return url;
    }

    public String getURL() {
        try {
            return entityInfo.findValue(URL_FIELD).asText();
        } catch (NullPointerException e) {
            return null;
        }
    }

    public String getQRCode() {
        try {
            return entityInfo.findValue(QR_CODE_FIELD).asText();
        } catch (NullPointerException e) {
            return null;
        }
    }


    public String getPinCode(){
        try{
            return entityInfo.get(SMS_PIN_FIELD).asText();
        }catch(NullPointerException e){
            return null;
        }

    }

    protected void setSchema(String customerId, String testingSiteId){
        entityInfo.put(USER_ID_FIELD, customerId);
        entityInfo.put(TESTING_SITE_ID_FIELD, testingSiteId);

        // 2022-05-18T07:12:37.742Z

        String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(System.currentTimeMillis()));
        entityInfo.put(START_TIME_FIELD, time);

    }

    /**
     * the function could be used when users need to mark that customers don't need QR code to be scanned(since they already have RAT kit)
     */
    public boolean updateRATKitInfo(boolean hasRATKit){
        additionalInfo.put(HAS_RAT_KIT_FIELD, hasRATKit);
        entityInfo.putPOJO(ADDITIONAL_INFO_FIELD, additionalInfo);

        WebServicesTarget webServicesTarget = new ServicesAdapter();
        String json = Utility.buildNestedJson(ADDITIONAL_INFO_FIELD, additionalInfo.toString());
        boolean result = false;
        try {
            result = webServicesTarget.patchData(Path.BOOKING.getPath(), json, getEntityId());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        responseMessage = webServicesTarget.getResponseMessage();
        return result;
    }

    private String getEntityId(){
        try {
            return entityInfo.get(ID_FIELD).asText();
        } catch (NullPointerException e) {
            return null;
        }
    }

    public void getBookingByQR(String qrCode){
        // get all the bookings
        ArrayList<ObjectNode> bookingList = new ArrayList<>();
        try {
            ObjectNode[] bookings = webServicesTarget.getAllData(Path.BOOKING.getPath(), null);
            responseMessage = webServicesTarget.getResponseMessage();
            bookingList = new ArrayList<>(Arrays.asList(bookings));

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        // if we find it by QR code, we store it in the entityInfo and additionalInfo
        for (ObjectNode booking : bookingList) {
            if (booking.findValue(QR_CODE_FIELD) != null ){
                if (booking.findValue(QR_CODE_FIELD).asText().equals(qrCode)) {
                    updateModel(booking);
                }
            }
        }
    }

    public void setState(ObjectNode entityInfo){
        super.updateModel(entityInfo);
    }


    protected boolean withinTimeLimit(){
        String startTime = entityInfo.findValue(START_TIME_FIELD).asText();
        // string to dataTime
        Date startDate = null;
        try {
            // UTC dateTime format
            startDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").parse(startTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        // get current time
        Date currentDate = new Date(System.currentTimeMillis());
        // if the start time is after the current time, it's within time limit
        return Objects.requireNonNull(startDate).after(currentDate);
    }

    protected boolean isCanceled(){
        // we assume the booking is not canceled if the state field of booking is not "canceled"
        String status = entityInfo.findValue(STATUS_FIELD).asText();
        return status.equals(CANCELED_STATUS);
    }



    protected boolean isUsed(){
        return !entityInfo.findValue("covidTests").isEmpty();
    }

    public boolean canBeChanged(String id){
        try {
            getSpecifiedEntity(Path.BOOKING.getPath(), id, Query.COVIDTESTS_IN_BOOKING.getQuery());
            responseMessage = webServicesTarget.getResponseMessage();
            // if the booking is used, we cannot change it
            // if the booking is canceled, we cannot change it
            // if the booking is not within time limit(the start date is earlier than today), we cannot change it
            // if the booking is not onsite booking, we cannot change it
            return !isCanceled() && withinTimeLimit() && !isUsed();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return false;

    }

    public void changeBooking(String venue, String startTime){
        if (!venue.equals("")){
            entityInfo.put(TESTING_SITE_ID_FIELD, venue);
        }
        if (!startTime.equals("")){
            entityInfo.put(START_TIME_FIELD, startTime);
        }

        try {
            webServicesTarget.patchData(Path.BOOKING.getPath(), entityInfo.toString(), getEntityId());
            responseMessage = webServicesTarget.getResponseMessage();
        }catch (IOException | InterruptedException e){
            e.printStackTrace();
        }
    }

}

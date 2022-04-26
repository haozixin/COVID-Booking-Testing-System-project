package engine;

import bookings.Booking;
import com.fasterxml.jackson.databind.JsonNode;
import testingSites.TestingSite;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Interface for DataCollection
 */
public interface DataCollection {
    /**
     * find the specific entity by its id or name
     * @param factor - based on which collection to search, nomally it is id or name
     * @return - the entity that matches the filter
     */
    Entity findEntity(String factor);

    /**
     * searches for other type of entity from this entity (search for inline entities)
     * Note: Each kind of entity has different factor to use for the search
     * @param factor - a field value to be used for the filter - it must be unique like id
     * @param query - choose one from Query Enum, a correct path to query the data
     * @return - the entity that matches the filter
     * @throws IOException
     * @throws InterruptedException
     */
    JsonNode searchForEntity(String factor, String query) throws IOException, InterruptedException;

    /**
     * prints the list of entities to the console
     */
    void printWholeList();

    /**
     *  fills the list with the data from the json file by using one factor at a time
     * @param field - the field name to be used for the filter
     * @param value - the value of the field to be used for the filter
     * @return  - the list of entities that match the filter
     */
    ArrayList<TestingSite> filterByOnFactor(String field, String value);

    /**
     *  Update the waiting time for Testing Site
     *  implemented in SitesCollection
     * @param siteId - the id of the site to be updated
     * @throws IOException
     * @throws InterruptedException
     */
    default void updateWaitingTime(String siteId) throws IOException, InterruptedException {}

    /**
     * Getting status(other information) of the site through input PIN code
     * - implemented in BookingsCollection
     * @param pinCode   - the pin code returned once a booking is confirmed
     * @return  - An entity type object ==> actually a booking entity/object
     */
    default Entity getStatusByPin(String pinCode){return null;}

    default String createOnsiteBooking(String type, String customerId, String siteId) throws IOException, InterruptedException {return null;}
    default Booking createHomeBooking(String type, String customerId, String siteId, boolean hasRATKit) throws IOException, InterruptedException {return null;}
    default boolean updateHasRATKit(String QRcode, boolean hasRATKit) throws IOException, InterruptedException{return false;}
}

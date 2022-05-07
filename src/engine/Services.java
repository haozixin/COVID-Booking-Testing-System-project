package engine;

import java.util.*;

/**
 * A thin wrapper for <code>java.util.ArrayList&lt;Action&gt;</code> that does not allow nulls to be added.
 * Reference FIT2099_Assignment1_v2
 */
public class Services implements Iterable<Service> {
    private ArrayList<Service> services = new ArrayList<Service>();

    /**
     * Constructs an empty list.
     */
    public Services() {
    }


    /**
     * Constructs a collection containing a single (non-null) Action.
     *
     * @param service the Action to add
     */
    public Services(Service service) {
        add(service);
    }


    /**
     * Appends the contents of another Actions list to this one.
     *
     * @param services the Actions to append
     */
    public void add(Services services) {
        for(Service service : services) {
            add(service);
        }
    }

    /**
     * Appends the contents of any List&lt;Action&gt; to this one.
     *
     * This overload allows the use of an unmodifiableList to prevent privacy leaks.
     * @param services the List&lt;Action&gt; to append
     */
    public void add(List<Service> services) {
        for (Service service : services) {
            add(service);
        }
    }

    /**
     * Appends a single Action to this collection, if it is non-null.  If it is null, then it is ignored.
     *
     * @param service the Action to append
     * @return true unconditionally
     */
    public boolean add(Service service) {
        if (service != null) {
            services.add(service);
        }
        return true;
    }

    /**
     * Returns an Iterator for the underlying collection.
     *
     * Implementing this method means that Actions implements the Iterable interface, which allows
     * you to use it in a foreach, e.g. <code>for (Action a: actions) {
     *    ...
     *    </code>
     *
     * @return an iterator
     * @see Iterable#iterator()
     */
    @Override
    public Iterator<Service> iterator() {
        return Collections.unmodifiableList(services).iterator();
    }

    /**
     * Returns a sorted copy of the list of Actions.
     *
     * @param comparator an object that can compare two Actions and determine their ordering
     * @return a sorted shallow copy of the list of Actions
     */
    public List<Service> sorted(Comparator<Service> comparator) {
        ArrayList<Service> sortedServices = new ArrayList<Service>(services);
        Collections.sort(sortedServices, comparator);
        return sortedServices;
    }

    /**
     * Delete the contents of this collection, leaving it empty.
     */
    public void clear() {
        services.clear();
    }

    /**
     * Count the number of Actions in the collection.
     *
     * @return the number of Actions in the collection.
     */
    public int size() {
        return services.size();
    }

    /**
     * Remove the first occurrence of an Action from the collection, if it is present.  If it is not present, the list is unchanged.
     *
     * @param service the Action to remove
     */
    public void remove(Service service) {
        services.remove(service);
    }

    /**
     * Return the <code>i</code>'th Action in the collection.
     *
     * @param i index of the Action to retrieve
     * @return the <code>i</code>'th Action in the collection
     * @throws IndexOutOfBoundsException when <code>i</code> &gt;= <code>this.size()</code>
     */
    public Service get(int i) {
        return services.get(i);
    }

    /**
     * Create and return an unmodifiable copy of the contents of the collection.
     *
     * @return an unmodifiable list of Action
     */
    public List<Service> getUnmodifiableActionList() {
        return Collections.unmodifiableList(services);
    }
}

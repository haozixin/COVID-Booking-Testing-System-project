package mementos;

public interface IMemento {
    /**
     * Restore data to old version
     */
    void restore();

    String getMetaData();

    /**
     * Check if the data is valid - dateTime is valid
     * @return
     */
    boolean isValid();
}

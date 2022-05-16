package mementos;

import com.fasterxml.jackson.databind.node.ObjectNode;

public interface IOriginator {
    /**
     * Same data as memento, data will become history and will be restored when system need it
     * @return A memento
     */
    IMemento save();

    /**
     * In our system, the state is the entityInfo attribute (all information stored in this attribute)
     * @return
     */
    ObjectNode getState();
}

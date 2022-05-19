package mementos;

import java.util.ArrayList;

public class Caretaker {
    public static final int HISTORY_SIZE = 3;
    private ArrayList<IMemento> histories = new ArrayList<>();



    public void undo() {
        if (histories.size() != 0) {
            IMemento memento = histories.remove(histories.size() - 1);
            memento.restore();
        }
    }

    public void addMemento(IMemento memento) {

        if (histories.size() < HISTORY_SIZE) {
            histories.add(memento);
            removeLastMemento();
        } else {
            histories.add(memento);
        }
    }

    private void removeLastMemento() {
        histories.remove(0);
    }




}

package mementos;

import models.BookingModel;

import java.util.ArrayList;

public class Caretaker {
    public static final int HISTORY_NUMBER = 3;
    private ArrayList<IMemento> histories = new ArrayList<>();
    private boolean isFull = false;

    public void undo() {
        if (histories.size() != 0) {
            IMemento memento = histories.get(histories.size() - 1);
            memento.restore();
            removeMemento();
        }
    }

    public void addMemento(IMemento memento) {

        if (histories.size() < HISTORY_NUMBER) {
            histories.add(memento);
        } else {
            isFull = true;
        }
    }

    private void removeMemento() {
        histories.remove(histories.size() - 1);
        isFull = false;
    }


}

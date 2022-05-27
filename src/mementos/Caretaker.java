package mementos;

import java.util.ArrayList;

/**
 * Concrete Caretaker class
 */
public abstract class Caretaker {
    public static final int HISTORY_SIZE = 3;
    protected ArrayList<IMemento> histories = new ArrayList<>();

    public void update(){
        for(IMemento memento: histories){
            if (!memento.isValid()){
                removeOneMemento(memento);
            }
        }
    }

    public boolean undo() {

        if (histories.size() != 0) {
            IMemento memento = histories.remove(histories.size() - 1);
            memento.restore();
            return true;
        }else{
            System.out.println("No more undo");
            return false;
        }
    }

    public void addMemento(IMemento memento) {

        if (histories.size() < HISTORY_SIZE) {
            histories.add(memento);
        } else {
            removeLastMemento();
            histories.add(memento);
        }
    }

    private void removeLastMemento() {
        histories.remove(0);
    }

    public ArrayList<IMemento> getHistories() {
        return histories;
    }

    public void removeOneMemento(IMemento memento){
        histories.remove(memento);
    }
}

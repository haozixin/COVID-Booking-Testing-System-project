package engine;

import models.Actor;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Reference FIT2099_Assignment1_v2
 * Menu class
 */
public class Menu {
    private Scanner keyboard = new Scanner(System.in);

    /**
     * Display a menu to the user and have them select an option.
     * Ignores more than 26 options. Go on, write a better one.
     *
     * @param actor the Actor representing the users
     * @param services the Actions that the user can choose from
     * @return the Action selected by the user
     */
    public Service showMenu(Actor actor, Services services) {
        ArrayList<Character> freeChars = new ArrayList<Character>();
        HashMap<Character, Service> keyToActionMap = new HashMap<Character, Service>();

        for (char i = 'a'; i <= 'z'; i++)
            freeChars.add(i);

        // Show with the actions with hotkeys first;
        for (Service service : services.sorted(new SortHotkeysFirst())) {
            String hotKey = service.hotkey();
            char c;
            if (hotKey == null || hotKey == "") {
                if (freeChars.isEmpty())
                    break; // we've run out of characters to pick from.
                c = freeChars.get(0);
            } else {
                c = hotKey.charAt(0);
            }
            freeChars.remove(Character.valueOf(c));
            keyToActionMap.put(c, service);
            System.out.println(c + ": " + service.menuDescription(actor));
        }

        char key;
        do {
            key = readChar();
        } while (!keyToActionMap.containsKey(key));

        return keyToActionMap.get(key);
    }

    private char readChar() {
        String s = keyboard.next();
        return s.charAt(0);
    }

    /**
     * Inner class that provides the ability to compare two Actions.
     *
     * This allows Actions to be sorted in order of their hotkeys.
     *
     */
    class SortHotkeysFirst implements Comparator<Service> {
        public int compare(Service a, Service b) {
            if (a.hotkey() != null && b.hotkey() == null)
                return -1;

            if (a.hotkey() == null && b.hotkey() != null)
                return 1;

            return 0;
        }
    }
}

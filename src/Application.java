import SystemFacade.CovidBAndTSystem;

import java.io.IOException;
import java.util.Scanner;

public class Application {
    public static void main(String[] args) {

        // system start/initial
        CovidBAndTSystem system = new CovidBAndTSystem();
        try {
            system.login();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }


    }

}

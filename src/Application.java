import SystemFacade.CovidBAndTSystemFacade;

public class Application {
    public static void main(String[] args) {
        System.out.println("Client");
        CovidBAndTSystemFacade covidBAndTSystemFacade = new CovidBAndTSystemFacade();
        covidBAndTSystemFacade.function();
    }
}

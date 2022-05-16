package subSystems;


import engine.CurrentOperator;
import services.GoBackService;
import services.InterviewService;

/**
 * This class represents the Onsite Testing Subsystem.
 */
public class OnsiteTestingSubsystem extends CovidBAndTSystem {

    public OnsiteTestingSubsystem() {
        super();
        systemName = "Onsite Testing Subsystem";
        setDashBoard();
    }

    @Override
    protected void processActorTurn(CurrentOperator currentOperator) {
        services.clear();
        if (currentOperator.isAdministrator() || currentOperator.isHealthcareWorker()){
            services.add(new InterviewService());
        }

        services.add(new GoBackService());


        super.processActorTurn(currentOperator);
    }
}

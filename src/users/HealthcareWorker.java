package users;

public class HealthcareWorker extends User {
    protected static final String IS_HEALTHCARE_WORKER_FIELD = "isHealthcareWorker";
    static{
        attributesTemplate.put(IS_HEALTHCARE_WORKER_FIELD, true);
    }

}

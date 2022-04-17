package users;

public class HealthcareWorker extends CurrentUser{
    protected static final String IS_HEALTHCARE_WORKER_FIELD = "isHealthcareWorker";
    static{
        attributesTemplate.put(IS_HEALTHCARE_WORKER_FIELD, true);
    }

}

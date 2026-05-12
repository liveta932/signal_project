package com.alerts;

/**
 * Factory for creating blood oxygen alerts.
 */

public class BloodOxygenAlertFactory extends AlertFactory {

    /**
     * Creates a blood oxygen alert with the given patient ID, condition, and timestamp.
     * @param patientId the ID of the patient
     * @param condition the condition for the alert
     * @param timestamp the time the alert was created
     * @return a new blood oxygen alert
     */

    @Override
    public Alert createAlert(String patientId, String condition, long timestamp) {
        return new BloodOxygenAlert(patientId, condition, timestamp);
    }
}
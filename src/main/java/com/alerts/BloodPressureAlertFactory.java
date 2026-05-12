package com.alerts;

/**
 * Factory class for creating blood pressure alerts.
 */

public class BloodPressureAlertFactory extends AlertFactory {

    /**
     * Creates a blood pressure alert with the given patient ID, condition, and timestamp.
     * @param patientId the ID of the patient
     * @param condition the condition for the alert
     * @param timestamp the time the alert was created
     * @return a new blood pressure alert
     */

    @Override
    public Alert createAlert(String patientId, String condition, long timestamp) {
        return new BloodPressureAlert(patientId, condition, timestamp);
    }
}
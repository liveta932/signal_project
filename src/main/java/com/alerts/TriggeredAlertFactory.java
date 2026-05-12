package com.alerts;

/**
 * Factory class for creating triggered alerts.
 */

public class TriggeredAlertFactory extends AlertFactory {

    /**
     * Creates a basic alert with the given patient details.
     * @param patientId the ID of the patient
     * @param condition the alert condition
     * @param timestamp the time the alert was created
     * @return a new basic alert
     */

    @Override
    public Alert createAlert(String patientId, String condition, long timestamp) {
        return new BasicAlert(patientId, condition, timestamp);
    }
}
package com.alerts;

/**
 * Abstract factory class for creating alerts.
 */

public abstract class AlertFactory {

    /**
     * Creates an alert with the given patient ID, condition, and timestamp.
     * @param patientId the ID of the patient
     * @param condition the condition for the alert
     * @param timestamp the time the alert was created
     * @return the created alert
     */

    public abstract Alert createAlert(String patientId, String condition, long timestamp);
}
package com.alerts;

/**
 * Factory class for creating ECG alerts.
 */

public class ECGAlertFactory extends AlertFactory {

    /**
     * Creates an ECG alert with the given patient ID, condition, and timestamp.
     * @param patientId the ID of the patient
     * @param condition the condition for the alert
     * @param timestamp the time the alert was created
     * @return a new ECG alert
     */

    @Override
    public Alert createAlert(String patientId, String condition, long timestamp) {
        return new ECGAlert(patientId, condition, timestamp);
    }
}
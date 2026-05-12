package com.alerts;

/**
 * Alert class for ECG related conditions.
 */

public class ECGAlert extends BasicAlert {

    /**
     * Creates an ECGAlert with patient ID, condition, and timestamp.
     * @param patientId the ID of the patient
     * @param condition the condition for the alert
     * @param timestamp the time the alert was created
     */

    public ECGAlert(String patientId, String condition, long timestamp) {
        super(patientId, condition, timestamp);
    }
}
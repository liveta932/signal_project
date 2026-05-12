package com.alerts;

/**
 * Alert class for blood oxygen related conditions.
 */

public class BloodOxygenAlert extends BasicAlert {

    /**
     * Creates a BloodOxygenAlert with patient ID, condition, and timestamp.
     * @param patientId the ID of the patient
     * @param condition the condition for the alert
     * @param timestamp the time the alert was created
     */

    public BloodOxygenAlert(String patientId, String condition, long timestamp) {
        super(patientId, condition, timestamp);
    }
}
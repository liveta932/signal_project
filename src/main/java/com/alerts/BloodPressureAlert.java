package com.alerts;

/**
 * Alert class for blood pressure related conditions.
 */

public class BloodPressureAlert extends BasicAlert {

    /**
     * Creates a BloodPressureAlert with patient ID, condition, and timestamp.
     * @param patientId the ID of the patient
     * @param condition the condition for the alert
     * @param timestamp the time the alert was created
     */

    public BloodPressureAlert(String patientId, String condition, long timestamp) {
        super(patientId, condition, timestamp);
    }
}
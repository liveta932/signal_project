package com.alerts;

// Represents an alert
/**
 * Basic alert class that stores patient alert details.
 */

public class BasicAlert implements Alert {

    private String patientId;
    private String condition;
    private long timestamp;

    /**
     * Creates a BasicAlert with patient ID, condition, and timestamp.
     * @param patientId the ID of the patient
     * @param condition the condition for the alert
     * @param timestamp the time the alert was created
     */

    public BasicAlert(String patientId, String condition, long timestamp) {
        this.patientId = patientId;
        this.condition = condition;
        this.timestamp = timestamp;
    }

    /**
     * Gets the patient ID.
     * @return the patient ID
     */

    public String getPatientId() {
        return patientId;
    }

    /**
     * Gets the alert condition.
     * @return the condition
     */

    public String getCondition() {
        return condition;
    }

    /**
     * Gets the alert timestamp.
     * @return the timestamp
     */

    public long getTimestamp() {
        return timestamp;
    }
}
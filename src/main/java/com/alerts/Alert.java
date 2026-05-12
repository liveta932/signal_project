package com.alerts;

/**
 * Represents a simple alert for a patient. Alert stores ID, condition and creation time.
 */

public interface Alert {

    /**
     * Gets the ID of the patient linked to this alert.
     * @return the patient ID
     */

    String getPatientId();

    /**
     * Gets the condition or problem related to this alert.
     * @return the alert condition
     */

    String getCondition();

    /**
     * Gets the time when the alert was created.
     * @return the alert timestamp
     */

    long getTimestamp();
}
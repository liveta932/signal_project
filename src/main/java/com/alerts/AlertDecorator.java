package com.alerts;

/**
 * Abstract decorator class for an Alert. It keeps an Alert object and passes method calls to it.
 */

public abstract class AlertDecorator implements Alert {

    protected Alert alert;

    /**
     * Creates an AlertDecorator with the given alert.
     * @param alert the alert to decorate
     */

    public AlertDecorator(Alert alert) { this.alert = alert; }

    /**
     * Gets the patient ID from the alert.
     * @return the patient ID
     */

    @Override
    public String getPatientId() { return alert.getPatientId();}

    /**
     * Gets the condition from the alert.
     * @return the condition
     */

    @Override
    public String getCondition() { return alert.getCondition(); }

    /**
     * Gets the timestamp from the alert.
     * @return the timestamp
     */

    @Override
    public long getTimestamp() { return alert.getTimestamp(); }
}
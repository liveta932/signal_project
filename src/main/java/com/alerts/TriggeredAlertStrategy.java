package com.alerts;

import data_management.PatientRecord;

import java.util.List;

/**
 * Strategy class for checking triggered alerts.
 */

public class TriggeredAlertStrategy implements AlertStrategy {

    private final TriggeredAlertRule triggeredAlertRule;

    public TriggeredAlertStrategy() { this.triggeredAlertRule = new TriggeredAlertRule(); }

    /**
     * Checks patient records for triggered alerts.
     * @param records the patient records to check
     * @return a list of triggered alerts
     */

    @Override
    public List<Alert> checkAlert(List<PatientRecord> records) { return triggeredAlertRule.check(records); }
}
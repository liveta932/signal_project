package com.alerts;

import data_management.PatientRecord;

import java.util.ArrayList;
import java.util.List;

/**
 * Rule class for checking patient or nurse triggered alerts.
 */

public class TriggeredAlertRule implements AlertRule {

    private final AlertFactory alertFactory;

    public TriggeredAlertRule() {
        this.alertFactory = new TriggeredAlertFactory();
    }

    /**
     * Checks patient records for triggered alerts.
     * @param records the patient records to check
     * @return a list of triggered alerts
     */

    @Override
    public List<Alert> check(List<PatientRecord> records) {
        List<Alert> alerts = new ArrayList<>();
        for (PatientRecord record : records) {
            if (record.getRecordType().equals("Alert") && record.getMeasurementValue() == 1.0) {
                alerts.add(alertFactory.createAlert(String.valueOf(record.getPatientId()), "Patient/nurse triggered alert", record.getTimestamp()));
            }
        }
        return alerts;
    }
}
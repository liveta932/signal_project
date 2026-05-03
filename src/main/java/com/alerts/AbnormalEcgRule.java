package com.alerts;

import data_management.PatientRecord;

import java.util.ArrayList;
import java.util.List;

/**
 * Checks for abnormal ECG peaks.
 */

public class AbnormalEcgRule implements AlertRule {

    private final AlertFactory alertFactory;

    public AbnormalEcgRule() {
        this.alertFactory = new ECGAlertFactory();
    }

    /**
     * Checks ECG records for abnormal peaks.
     *
     * @param records the patient records to check
     * @return alerts for abnormal ECG values
     */

    @Override
    public List<Alert> check(List<PatientRecord> records) {
        List<Alert> alerts = new ArrayList<>();
        List<PatientRecord> ecgRecords = new ArrayList<>();
        for (PatientRecord record : records) {
            if (record.getRecordType().equals("ECG")) {
                ecgRecords.add(record);
            }
        }
        if (ecgRecords.size() < 2) { return alerts; }
        double total = 0;
        for (PatientRecord record : ecgRecords) {
            total = total + Math.abs(record.getMeasurementValue());
        }
        double average = total / ecgRecords.size();
        for (PatientRecord record : ecgRecords) {
            double value = Math.abs(record.getMeasurementValue());
            if (value > average * 2 && value > 1.0) {
                alerts.add(alertFactory.createAlert(
                        String.valueOf(record.getPatientId()), "Abnormal ECG peak", record.getTimestamp()));
            }
        }
        return alerts;
    }
}
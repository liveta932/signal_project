package com.alerts;

import data_management.PatientRecord;

import java.util.ArrayList;
import java.util.List;

/**
 * Checks for abnormal ECG peaks.
 *
 * Assumption: The assignment says to compare ECG peaks with the moving average, but it does not give an exact threshold.
 * In this implementation an alert is created when an ECG value is more than twice the average absolute ECG value
 * and is also above 1.0.
 */

public class AbnormalEcgRule implements AlertRule {

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
                alerts.add(new Alert(String.valueOf(record.getPatientId()),
                        "Abnormal ECG peak", record.getTimestamp()));
            }
        }

        return alerts;
    }
}
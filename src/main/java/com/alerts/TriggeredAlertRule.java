package com.alerts;

import data_management.PatientRecord;

import java.util.ArrayList;
import java.util.List;

public class TriggeredAlertRule implements AlertRule {

    @Override
    public List<Alert> check(List<PatientRecord> records) {
        List<Alert> alerts = new ArrayList<>();

        for (PatientRecord record : records) {
            if (record.getRecordType().equals("Alert") && record.getMeasurementValue() == 1.0) {
                alerts.add(new Alert(
                        String.valueOf(record.getPatientId()),
                        "Patient/nurse triggered alert",
                        record.getTimestamp()
                ));
            }
        }

        return alerts;
    }
}
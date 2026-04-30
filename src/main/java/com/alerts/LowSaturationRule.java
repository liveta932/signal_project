package com.alerts;

import data_management.PatientRecord;
import java.util.ArrayList;
import java.util.List;

/**
 * Checks for low blood oxygen saturation. An alert is created if saturation falls below 92%.
 */

public class LowSaturationRule implements AlertRule {

    /**
     * Checks records for low saturation values.
     *
     * @param records the patient records to check
     * @return alerts for low saturation
     */

    @Override
    public List<Alert> check(List<PatientRecord> records) {
        List<Alert> alerts = new ArrayList<>();

        for (PatientRecord record : records) {
            if (record.getRecordType().equals("Saturation") && record.getMeasurementValue() < 92) {
                alerts.add(new Alert(String.valueOf(record.getPatientId()),
                        "Low blood oxygen saturation", record.getTimestamp()));
            }
        }

        return alerts;
    }
}
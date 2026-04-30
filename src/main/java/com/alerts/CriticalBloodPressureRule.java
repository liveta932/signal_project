package com.alerts;

import data_management.PatientRecord;

import java.util.ArrayList;
import java.util.List;

/**
 * Checks for critical blood pressure values. Alert is created if systolic blood pressure is more than 180 or less than 90,
 * or if diastolic blood pressure is more than 120 or less than 60.
 */

public class CriticalBloodPressureRule implements AlertRule {

    /**
     * Checks for critical blood pressure values.
     *
     * @param records the patient records to check
     * @return alerts for critical blood pressure values
     */

    @Override
    public List<Alert> check(List<PatientRecord> records) {
        List<Alert> alerts = new ArrayList<>();

        for (PatientRecord record : records) {
            String type = record.getRecordType();
            double value = record.getMeasurementValue();

            if ((type.equals("SystolicPressure") && (value > 180 || value < 90))) {
                alerts.add(new Alert(String.valueOf(record.getPatientId()),
                        "Critical systolic blood pressure", record.getTimestamp()));
            }

            if ((type.equals("DiastolicPressure") && (value > 120 || value < 60))) {
                alerts.add(new Alert(String.valueOf(record.getPatientId()),
                        "Critical diastolic blood pressure", record.getTimestamp()));
            }
        }
            return alerts;
        }
    }
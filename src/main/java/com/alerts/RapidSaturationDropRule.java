package com.alerts;

import data_management.PatientRecord;

import java.util.ArrayList;
import java.util.List;

/**
 * Checks for a rapid drop in blood oxygen saturation. An alert is created if saturation drops by 5% or more within 10 minutes.
 */

public class RapidSaturationDropRule implements AlertRule {

    private final AlertFactory alertFactory;

    public RapidSaturationDropRule() {
        this.alertFactory = new BloodOxygenAlertFactory();
    }

    /**
     * Checks records for a rapid saturation drop.
     *
     * @param records the patient records to check
     * @return alerts for rapid saturation drops
     */

    @Override
    public List<Alert> check(List<PatientRecord> records) {
        List<Alert> alerts = new ArrayList<>();
        List<PatientRecord> saturationRecords = new ArrayList<>();
        for (PatientRecord record : records) {
            if (record.getRecordType().equals("Saturation")) {
                saturationRecords.add(record);
            }
        }
        for (int i = 0; i < saturationRecords.size() - 1; i++) {
            PatientRecord first = saturationRecords.get(i);
            PatientRecord second = saturationRecords.get(i + 1);
            long timeDifference = second.getTimestamp() - first.getTimestamp();
            double drop = first.getMeasurementValue() - second.getMeasurementValue();
            if (timeDifference <= 10 * 60 * 1000 && drop >= 5) {
                alerts.add(alertFactory.createAlert(
                        String.valueOf(second.getPatientId()), "Rapid drop in blood oxygen saturation", second.getTimestamp()));
            }
        }
        return alerts;
    }
}
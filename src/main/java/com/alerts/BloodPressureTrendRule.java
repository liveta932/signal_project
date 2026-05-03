package com.alerts;

import data_management.PatientRecord;

import java.util.ArrayList;
import java.util.List;

/**
 * Checks for increasing or decreasing blood pressure trends. An alert is created if three consecutive readings increase or decrease by more than 10 mmHg each.
 */

public class BloodPressureTrendRule implements AlertRule {

    private final AlertFactory alertFactory;

    public BloodPressureTrendRule() {
        this.alertFactory = new BloodPressureAlertFactory();
    }

    /**
     * Checks systolic and diastolic pressure records for trends.
     *
     * @param records the patient records to check
     * @return alerts for blood pressure trends
     */

    @Override
    public List<Alert> check(List<PatientRecord> records) {
        List<Alert> alerts = new ArrayList<>();
        alerts.addAll(checkTrend(records, "SystolicPressure"));
        alerts.addAll(checkTrend(records, "DiastolicPressure"));
        return alerts;
    }

    /**
     * Checks one type of pressure for a trend.
     *
     * @param records the patient records to check
     * @param pressureType the type of pressure to check
     * @return alerts for trends of the given pressure type
     */

    private List<Alert> checkTrend(List<PatientRecord> records, String pressureType) {
        List<Alert> alerts = new ArrayList<>();
        List<PatientRecord> pressureRecords = new ArrayList<>();

        for (PatientRecord record : records) {
            if (record.getRecordType().equals(pressureType)) {
                pressureRecords.add(record);
            }
        }

        for (int i = 0; i < pressureRecords.size() - 2; i++) {
            PatientRecord first = pressureRecords.get(i);
            PatientRecord second = pressureRecords.get(i + 1);
            PatientRecord third = pressureRecords.get(i + 2);
            double changeOne = second.getMeasurementValue() - first.getMeasurementValue();
            double changeTwo = third.getMeasurementValue() - second.getMeasurementValue();
            if (changeOne > 10 && changeTwo > 10) {
                alerts.add(alertFactory.createAlert(
                        String.valueOf(third.getPatientId()), "Increasing trend of " + pressureType, third.getTimestamp()));
            }

            if (changeOne < -10 && changeTwo < -10) {
                alerts.add(alertFactory.createAlert(
                        String.valueOf(third.getPatientId()), "Decreasing trend of " + pressureType, third.getTimestamp()));
            }
        }
        return alerts;
    }
}
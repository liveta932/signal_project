package com.alerts;

import data_management.PatientRecord;

import java.util.ArrayList;
import java.util.List;

/**
 * Checks for hypotensive hypoxemia. This alert is created when systolic pressure is below 90
 * and blood oxygen saturation is below 92%.
 */
public class HypotensiveHypoxemiaRule implements AlertRule {

    /**
     * Checks records for the combined hypotensive hypoxemia condition.
     *
     * @param records the patient records to check
     * @return alerts for hypotensive hypoxemia
     */

    @Override
    public List<Alert> check(List<PatientRecord> records) {
        List<Alert> alerts = new ArrayList<>();

        PatientRecord lowPressureRecord = null;
        PatientRecord lowSaturationRecord = null;

        for (PatientRecord record : records) {
            if (record.getRecordType().equals("SystolicPressure") && record.getMeasurementValue() < 90) {
                lowPressureRecord = record;
            }
            if (record.getRecordType().equals("Saturation") && record.getMeasurementValue() < 92) {
                lowSaturationRecord = record;
            }
        }

        if (lowPressureRecord != null && lowSaturationRecord != null) {
            long alertTime = Math.max(lowPressureRecord.getTimestamp(), lowSaturationRecord.getTimestamp());

            alerts.add(new Alert(String.valueOf(lowPressureRecord.getPatientId()),
                    "Hypotensive hypoxemia alert", alertTime));
        }

        return alerts;
    }
}
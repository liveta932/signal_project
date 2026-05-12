package com.alerts;

import data_management.PatientRecord;

import java.util.ArrayList;
import java.util.List;

/**
 * Strategy class for checking blood pressure alerts.
 */

public class BloodPressureStrategy implements AlertStrategy {

    private final CriticalBloodPressureRule criticalBloodPressureRule;
    private final BloodPressureTrendRule bloodPressureTrendRule;

    public BloodPressureStrategy() {
        this.criticalBloodPressureRule = new CriticalBloodPressureRule();
        this.bloodPressureTrendRule = new BloodPressureTrendRule();
    }

    /**
     * Checks patient records for blood pressure alerts.
     * @param records the patient records to check
     * @return a list of blood pressure alerts
     */
    @Override
    public List<Alert> checkAlert(List<PatientRecord> records) {
        List<Alert> alerts = new ArrayList<>();
        alerts.addAll(criticalBloodPressureRule.check(records));
        alerts.addAll(bloodPressureTrendRule.check(records));
        return alerts;
    }
}
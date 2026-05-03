package com.alerts;

import data_management.PatientRecord;

import java.util.ArrayList;
import java.util.List;

public class BloodPressureStrategy implements AlertStrategy {

    private final CriticalBloodPressureRule criticalBloodPressureRule;
    private final BloodPressureTrendRule bloodPressureTrendRule;

    public BloodPressureStrategy() {
        this.criticalBloodPressureRule = new CriticalBloodPressureRule();
        this.bloodPressureTrendRule = new BloodPressureTrendRule();
    }

    @Override
    public List<Alert> checkAlert(List<PatientRecord> records) {
        List<Alert> alerts = new ArrayList<>();
        alerts.addAll(criticalBloodPressureRule.check(records));
        alerts.addAll(bloodPressureTrendRule.check(records));
        return alerts;
    }
}
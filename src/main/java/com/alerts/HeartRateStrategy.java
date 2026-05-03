package com.alerts;

import data_management.PatientRecord;
import java.util.List;

public class HeartRateStrategy implements AlertStrategy {

    private final AbnormalEcgRule abnormalEcgRule;

    public HeartRateStrategy() {
        this.abnormalEcgRule = new AbnormalEcgRule();
    }

    @Override
    public List<Alert> checkAlert(List<PatientRecord> records) {
        return abnormalEcgRule.check(records);
    }
}
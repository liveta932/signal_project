package com.alerts;

import data_management.PatientRecord;
import java.util.List;

/**
 * Strategy class for checking heart rate alerts.
 */

public class HeartRateStrategy implements AlertStrategy {

    private final AbnormalEcgRule abnormalEcgRule;

    /**
     * Creates a HeartRateStrategy with the abnormal ECG rule.
     */

    public HeartRateStrategy() {
        this.abnormalEcgRule = new AbnormalEcgRule();
    }

    /**
     * Checks patient records for heart rate alerts.
     * @param records the patient records to check
     * @return a list of heart rate alerts
     */

    @Override
    public List<Alert> checkAlert(List<PatientRecord> records) {
        return abnormalEcgRule.check(records);
    }
}
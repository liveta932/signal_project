package com.alerts;

import data_management.PatientRecord;
import java.util.List;

/**
 * Represents one alert rule. Each alert rule checks patient records for one
 * specific medical condition.
 */

public interface AlertRule {

    /**
     * Checks patient records and returns any alerts that should be created.
     *
     * @param records the patient records to check
     * @return a list of alerts created by this rule
     */

    List<Alert> check(List<PatientRecord> records);
}

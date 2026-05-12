package com.alerts;

import data_management.PatientRecord;
import java.util.List;

/**
 * Strategy interface for checking patient records and creating alerts.
 */

public interface AlertStrategy {

    /**
     * Checks a list of patient records for alert conditions.
     * @param records the patient records to check
     * @return a list of alerts found from the records
     */

    List<Alert> checkAlert(List<PatientRecord> records);
}
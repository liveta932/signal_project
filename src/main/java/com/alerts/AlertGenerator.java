package com.alerts;

import data_management.DataStorage;
import data_management.Patient;
import data_management.PatientRecord;
import java.util.ArrayList;
import java.util.List;

/**
 * The AlertGenerator class is responsible for monitoring patient data and generating alerts when certain predefined conditions are met.
 */

public class AlertGenerator {
    private DataStorage dataStorage;
    private List<Alert> alerts;
    private List<AlertStrategy> strategies;

    /**
     * Constructs an AlertGenerator with a specified DataStorage.
     *
     * @param dataStorage the data storage system that provides access to patient data
     */

    public AlertGenerator(DataStorage dataStorage) {
        this.dataStorage = dataStorage;
        this.alerts = new ArrayList<>();
        this.strategies = new ArrayList<>();

        strategies.add(new BloodPressureStrategy());
        strategies.add(new OxygenSaturationStrategy());
        strategies.add(new HeartRateStrategy());
    }

    /**
     * Evaluates the specified patient's data to determine if any alert conditions are met.
     *
     * @param patient the patient data to evaluate for alert conditions
     */

    public void evaluateData(Patient patient) {
        List<PatientRecord> records = patient.getRecords(0, System.currentTimeMillis());
        for (AlertStrategy strategy : strategies) {
            List<Alert> createdAlerts = strategy.checkAlert(records);
            for (Alert alert : createdAlerts) {
                triggerAlert(alert);
            }
        }
    }

    /**
     * Triggers an alert for the monitoring system.
     */

    private void triggerAlert(Alert alert) {
        Alert decoratedAlert = alert;
        String condition = alert.getCondition().toLowerCase();
        if (condition.contains("critical") || condition.contains("hypotensive") || condition.contains("rapid") || condition.contains("ecg")) {
            decoratedAlert = new PriorityAlertDecorator(decoratedAlert, "HIGH");
        }
        if (condition.contains("trend") || condition.contains("triggered")) {
            decoratedAlert = new RepeatedAlertDecorator(decoratedAlert, 3, 60000);
        }
        alerts.add(decoratedAlert);
    }

    public List<Alert> getAlerts() {
        return alerts;
    }
}
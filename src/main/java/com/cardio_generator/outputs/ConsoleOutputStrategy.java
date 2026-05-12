package com.cardio_generator.outputs;

/**
 * Output strategy that prints patient data to the console.
 */

public class ConsoleOutputStrategy implements OutputStrategy {

    /**
     * Prints patient data using the patient ID, timestamp, label, and data.
     * @param patientId the ID of the patient
     * @param timestamp the time of the data
     * @param label the label for the data
     * @param data the data value
     */

    @Override
    public void output(int patientId, long timestamp, String label, String data) {
        System.out.printf("Patient ID: %d, Timestamp: %d, Label: %s, Data: %s%n", patientId, timestamp, label, data);
    }
}
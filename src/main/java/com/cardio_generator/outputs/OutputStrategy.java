package com.cardio_generator.outputs;

/**
 * Defines how generated data is output.
 */

public interface OutputStrategy {

    /**
     * Outputs data for a patient.
     *
     * @param patientId ID of the patient
     * @param timestamp time of the data
     * @param label type of data
     * @param data generated value
     */

    void output(int patientId, long timestamp, String label, String data);
}

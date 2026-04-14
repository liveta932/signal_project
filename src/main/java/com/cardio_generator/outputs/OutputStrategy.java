package com.cardio_generator.outputs;


/**
 * Defines how generated data is sent or written.
 * This interface is used to separate data generation from data delivery. Different implementations can output the same generated data in different
 * ways, for example to the console, to a file, or through a network connection.
 */
public interface OutputStrategy {

    /**
     * Outputs one generated data value for a patient.
     *
     * @param patientId ID of the patient
     * @param timestamp time when the data was generated
     * @param label type of data, for example ECG or BloodPressure
     * @param data generated value
     */

    void output(int patientId, long timestamp, String label, String data);
}

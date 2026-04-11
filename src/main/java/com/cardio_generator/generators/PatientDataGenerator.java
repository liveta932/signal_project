package com.cardio_generator.generators;

import com.cardio_generator.outputs.OutputStrategy;

/**
 * Defines a generator for patient data.
 */

public interface PatientDataGenerator {

    /**
     * Generates data for one patient.
     *
     * @param patientId ID of the patient
     * @param outputStrategy output method for the generated data
     */

    void generate(int patientId, OutputStrategy outputStrategy);
}

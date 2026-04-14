package com.cardio_generator.generators;

import com.cardio_generator.outputs.OutputStrategy;

/**
 * Defines a common interface for classes that generate patient data.
 * This interface is used by all simulator data generators, so different types of health data can be produced in a consistent way. Each generator
 * creates data for a patient and sends the result using an OutputStrategy.
 */

public interface PatientDataGenerator {

    /**
     * Generates data for one patient and passes it to the selected output.
     *
     * @param patientId ID of the patient
     * @param outputStrategy output method for the generated data
     */

    void generate(int patientId, OutputStrategy outputStrategy);
}

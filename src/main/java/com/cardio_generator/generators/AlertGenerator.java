package com.cardio_generator.generators;

import java.util.Random;

import com.cardio_generator.outputs.OutputStrategy;

/**
 * Generates simulated alert events for patients.
 * This class does not check real medical conditions. Instead, it randomly simulates whether an alert becomes triggered or resolved for each patient.
 * It keeps track of the current alert state so the output changes in a more realistic way over time.
 */
public class AlertGenerator implements PatientDataGenerator {

    // changed the name of final variable
    public static final Random RANDOM_GENERATOR = new Random();

    // changed the name to camelCase for variables, and to final
    private final boolean[] alertStates; // false = resolved, true = pressed


    /**
     * Creates an alert generator.
     * Initializes the alert state for all patients. Each patient starts with no active alert.
     * @param patientCount number of patients
     */

    // added 'this' for clarity
    public AlertGenerator(int patientCount) {
        this.alertStates = new boolean[patientCount + 1];
    }

    /**
     * Generates alert data for one patient.
     * If the patient already has an active alert, there is a high chance the alert will be resolved. If there is no active alert, there is a smaller
     * random chance that a new alert will be triggered. The result is then sent using the selected output strategy.
     * @param patientId ID of the patient
     * @param outputStrategy output method for the generated data
     * @throws ArrayIndexOutOfBoundsException if the patient ID is outside the valid range
     */
    @Override
    public void generate(int patientId, OutputStrategy outputStrategy) {
        try {
            if (alertStates[patientId]) {
                if (RANDOM_GENERATOR.nextDouble() < 0.9) { // 90% chance to resolve
                    alertStates[patientId] = false;
                    // Output the alert
                    outputStrategy.output(patientId, System.currentTimeMillis(), "Alert", "resolved");
                }
            } else {
                // changed the same to camelCase because it is a local variable
                double lambda = 0.1; // Average rate (alerts per period), adjust based on desired frequency
                double p = -Math.expm1(-lambda); // Probability of at least one alert in the period
                boolean alertTriggered = RANDOM_GENERATOR.nextDouble() < p;

                if (alertTriggered) {
                    alertStates[patientId] = true;
                    // Output the alert
                    outputStrategy.output(patientId, System.currentTimeMillis(), "Alert", "triggered");
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            // Replaced a broad Exception catch with a specific exception for invalid patientId access
            System.err.println("Invalid patient ID: " + patientId);
            e.printStackTrace();
        }
    }
}

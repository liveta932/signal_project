package com.cardio_generator.generators;

import java.util.Random;

import com.cardio_generator.outputs.OutputStrategy;

public class AlertGenerator implements PatientDataGenerator {

    // changed the name of final variable
    public static final Random RANDOM_GENERATOR = new Random();

    // changed the name to camelCase for variables
    private boolean[] alertStates; // false = resolved, true = pressed

    // added 'this' for clarity
    public AlertGenerator(int patientCount) {
        this.alertStates = new boolean[patientCount + 1];
    }

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

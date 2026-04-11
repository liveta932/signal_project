package com.cardio_generator.outputs;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Writes generated data to files.
 */

// Changed class name to UpperCamelCase
public class FileOutputStrategy implements OutputStrategy {

    // Changed this name to follow camelCase rule for variables naming
    // changed to final because it is being assigned a value only once
    private final String baseDirectory;

    // Changed file_map to fileMap to follow cameCase rule for variables naming
    // changed visibility to private to preserve encapsulation to internal state
    private final ConcurrentHashMap<String, String> fileMap = new ConcurrentHashMap<>();


    /**
     * Creates a file output strategy.
     *
     * @param baseDirectory directory for output files
     */

    // Changed constructor name to UpperCamelCase
    public FileOutputStrategy(String baseDirectory) {

        this.baseDirectory = baseDirectory;
    }

    /**
     * Writes patient data to a file.
     *
     * @param patientId ID of the patient
     * @param timestamp time of the data
     * @param label type of data
     * @param data generated value
     */

    @Override
    public void output(int patientId, long timestamp, String label, String data) {
        try {
            // Create the directory
            Files.createDirectories(Paths.get(baseDirectory));
        } catch (IOException e) {
            System.err.println("Error creating base directory: " + e.getMessage());
            return;
        }
        // Set the FilePath variable
        // change this variable name to camelCase rule for naming variables
        String filePath = fileMap.computeIfAbsent(label, k -> Paths.get(baseDirectory, label + ".txt").toString());

        // Write the data to the file
        try (PrintWriter out = new PrintWriter(
                Files.newBufferedWriter(Paths.get(filePath), StandardOpenOption.CREATE, StandardOpenOption.APPEND))) {
            out.printf("Patient ID: %d, Timestamp: %d, Label: %s, Data: %s%n", patientId, timestamp, label, data);
        } catch (Exception e) {
            System.err.println("Error writing to file " + filePath + ": " + e.getMessage());
        }
    }
}
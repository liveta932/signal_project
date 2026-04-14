package com.cardio_generator.outputs;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Writes generated patient data to text files.
 * This class is one implementation of OutputStrategy. Instead of printing data to the console or sending it over the network, it saves the generated
 * values into files. A separate file is used for each label type, so for example ECG data and blood pressure data are stored in different files.
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
     * Writes one generated data entry to a file.
     * If the output directory does not exist, it is created first. The file used depends on the label, so each type of generated data can be stored
     * in its own file. New data is appended instead of replacing old content.
     * @param patientId ID of the patient
     * @param timestamp time when the data was generated
     * @param label type of generated data
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
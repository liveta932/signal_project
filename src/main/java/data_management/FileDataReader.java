package data_management;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class FileDataReader implements DataReader {
    private final String directoryPath;

    /**
     * Reads patient data from files in a folder and stores it in a DataStorage object.
     */

    public FileDataReader(String directoryPath) {
        this.directoryPath = directoryPath;
    }

    /**
     * Reads all files from the folder and stores the data in DataStorage.
     *
     * @param dataStorage the storage object where the patient data will be saved
     * @throws IOException if the folder cannot be found or read
     */

    @Override
    public void readData(DataStorage dataStorage) throws IOException {
        File folder = new File(directoryPath);
        File[] listOfFiles = folder.listFiles();

        if (listOfFiles==null) {
            throw new IOException("Could not read the folder " + directoryPath);
        }

        for (File file : listOfFiles) {
            if (file.isFile()) {
                readFile(file, dataStorage);
            }
        }

    }

    /**
     * Reads one file line by line.
     *
     * @param file the file that will be read
     * @param dataStorage the storage object where the data will be saved
     * @throws IOException if the file cannot be opened or read
     */

    private void readFile(File file, DataStorage dataStorage) throws IOException {
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            readLine(line, dataStorage);
        }
        scanner.close();
    }

    /**
     * Reads one line of text, gets the patient data from it, and stores it.
     *
     * @param line one line from the data file
     * @param dataStorage the storage object where the data will be saved
     */

    private void readLine(String line, DataStorage dataStorage) {
        try {
            String[] parts = line.split(",");
            String patientPart = parts[0];
            String timePart = parts[1];
            String labelPart = parts[2];
            String dataPart = parts[3];
            int patientId = Integer.parseInt(patientPart.split(":")[1].trim());
            long timestamp = Long.parseLong(timePart.split(":")[1].trim());
            String label = labelPart.split(":")[1].trim();
            String dataText = dataPart.split(":")[1].trim();
            double value;

            if (dataText.equalsIgnoreCase("triggered")) { value = 1.0; }
            else if (dataText.equalsIgnoreCase("resolved")) { value = 0.0;}
            else {
                dataText = dataText.replace("%", "");
                value = Double.parseDouble(dataText);
            }

            dataStorage.addPatientData(patientId, value, label, timestamp);
        } catch (Exception e) {
            System.err.println("Could not read this line: " + line);
        }
    }

}

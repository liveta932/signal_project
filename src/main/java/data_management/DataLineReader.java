package data_management;

public class DataLineReader {

    /**
     * Reads one line/message of text and stores it in DataStorage.
     * @param line one line or message of data
     * @param dataStorage the storage object where the data will be saved
     */

    public void readLine(String line, DataStorage dataStorage) {
        try {
            String[] parts = line.split(",", 4);
            if (parts.length != 4) {
                System.err.println("Could not read this line: " + line);
                return;
            }

            int patientId;
            long timestamp;
            String label;
            String dataText;

            if (line.startsWith("Patient ID:")) {
                String patientPart = parts[0];
                String timePart = parts[1];
                String labelPart = parts[2];
                String dataPart = parts[3];

                patientId = Integer.parseInt(patientPart.split(":")[1].trim());
                timestamp = Long.parseLong(timePart.split(":")[1].trim());
                label = labelPart.split(":")[1].trim();
                dataText = dataPart.split(":")[1].trim();
            } else {
                patientId = Integer.parseInt(parts[0].trim());
                timestamp = Long.parseLong(parts[1].trim());
                label = parts[2].trim();
                dataText = parts[3].trim();
            }

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
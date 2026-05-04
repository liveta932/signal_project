package data_management;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class FileDataReader implements DataReader {
    private final String directoryPath;
    private final DataLineReader lineReader = new DataLineReader();

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
            lineReader.readLine(line, dataStorage);
        }
        scanner.close();
    }
}

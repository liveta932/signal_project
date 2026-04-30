package data_management;

import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests the FileDataReader class that checks if FileDataReader can read patient data from a file
 * and store it correctly in a DataStorage object.
 */

public class FileDataReaderTest {

    /**
     * Tests reading patient data from a file. It creates a temporary folder and file, writes two ECG records
     * into the file, reads the file using FileDataReader, and then checks if the records were stored correctly
     * in DataStorage.
     *
     * @throws IOException if the test file or folder cannot be created or read
     */

    @Test
    public void testReadData() throws IOException {
        File folder = new File("test_output");
        folder.mkdir();
        File file = new File(folder, "ECG.txt");
        FileWriter writer = new FileWriter(file);

        writer.write("Patient ID: 1, Timestamp: 1714376789050, Label: ECG, Data: 0.45\n");
        writer.write("Patient ID: 1, Timestamp: 1714376789051, Label: ECG, Data: 0.50\n");
        writer.close();

        DataStorage dataStorage = new DataStorage();
        FileDataReader fileDataReader = new FileDataReader("test_output");
        fileDataReader.readData(dataStorage);

        List<PatientRecord> records = dataStorage.getRecords(1, 1714376789050L, 1714376789051L);

        assertEquals(2, records.size());
        assertEquals(0.45, records.get(0).getMeasurementValue());
        assertEquals("ECG", records.get(0).getRecordType());
    }
}

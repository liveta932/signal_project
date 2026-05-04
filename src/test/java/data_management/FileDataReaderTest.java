package data_management;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FileDataReaderTest {

    private DataStorage storage;

    @TempDir
    Path tempDirectory;

    @BeforeEach
    public void setUp() {
        storage = DataStorage.getInstance();
        storage.clear();
    }

    @Test
    public void testReadDataFromFile() throws Exception {
        File file = tempDirectory.resolve("ECG.txt").toFile();
        PrintWriter writer = new PrintWriter(file);
        writer.println("Patient ID: 1, Timestamp: 1000, Label: ECG, Data: 0.5");
        writer.println("Patient ID: 1, Timestamp: 2000, Label: ECG, Data: 0.6");
        writer.close();
        FileDataReader reader = new FileDataReader(tempDirectory.toString());
        reader.readData(storage);
        List<PatientRecord> records = storage.getRecords(1, 0, 3000);

        assertEquals(2, records.size());
        assertEquals("ECG", records.get(0).getRecordType());
        assertEquals(0.5, records.get(0).getMeasurementValue());
        assertEquals(0.6, records.get(1).getMeasurementValue());
    }
}
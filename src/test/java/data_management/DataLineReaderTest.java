package data_management;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests that data lines are read correctly and stored as patient records.
 */

public class DataLineReaderTest {

    private DataStorage storage;
    private DataLineReader lineReader;

    /**
     * Sets up clean storage and a new line reader before each test.
     */

    @BeforeEach
    public void setUp() {
        storage = DataStorage.getInstance();
        storage.clear();
        lineReader = new DataLineReader();
    }

    /**
     * Tests that websocket format data is read correctly.
     */

    @Test
    public void testReadWebSocketFormat() {
        lineReader.readLine("1,1000,ECG,0.5", storage);
        List<PatientRecord> records = storage.getRecords(1, 0, 2000);

        assertEquals(1, records.size());
        assertEquals(1, records.get(0).getPatientId());
        assertEquals("ECG", records.get(0).getRecordType());
        assertEquals(0.5, records.get(0).getMeasurementValue());
        assertEquals(1000, records.get(0).getTimestamp());
    }

    /**
     * Tests that file format data is read correctly.
     */

    @Test
    public void testReadFileFormat() {
        lineReader.readLine("Patient ID: 2, Timestamp: 2000, Label: Saturation, Data: 98%", storage);
        List<PatientRecord> records = storage.getRecords(2, 0, 3000);

        assertEquals(1, records.size());
        assertEquals(2, records.get(0).getPatientId());
        assertEquals("Saturation", records.get(0).getRecordType());
        assertEquals(98.0, records.get(0).getMeasurementValue());
        assertEquals(2000, records.get(0).getTimestamp());
    }

    /**
     * Tests that a triggered alert is read correctly.
     */

    @Test
    public void testReadTriggeredAlert() {
        lineReader.readLine("3,3000,Alert,triggered", storage);
        List<PatientRecord> records = storage.getRecords(3, 0, 4000);

        assertEquals(1, records.size());
        assertEquals("Alert", records.get(0).getRecordType());
        assertEquals(1.0, records.get(0).getMeasurementValue());
    }

    /**
     * Tests that a resolved alert is read correctly.
     */

    @Test
    public void testReadResolvedAlert() {
        lineReader.readLine("4,4000,Alert,resolved", storage);
        List<PatientRecord> records = storage.getRecords(4, 0, 5000);

        assertEquals(1, records.size());
        assertEquals("Alert", records.get(0).getRecordType());
        assertEquals(0.0, records.get(0).getMeasurementValue());
    }
}
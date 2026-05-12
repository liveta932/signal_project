package data_management;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests that websocket style messages are stored correctly.
 */

public class WebSocketDataReaderIntegrationTest {

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
     * Tests that one websocket style message is stored correctly.
     */

    @Test
    public void testWebSocketStyleMessageIsStored() {
        lineReader.readLine("1,1000,ECG,0.5", storage);
        List<PatientRecord> records = storage.getRecords(1, 0, 2000);

        assertEquals(1, records.size());
        assertEquals(1, records.get(0).getPatientId());
        assertEquals("ECG", records.get(0).getRecordType());
        assertEquals(0.5, records.get(0).getMeasurementValue());
        assertEquals(1000L, records.get(0).getTimestamp());
    }

    /**
     * Tests that more than one websocket style message is stored correctly.
     */

    @Test
    public void testMoreThanOneWebSocketStyleMessageIsStored() {
        lineReader.readLine("1,1000,ECG,0.5", storage);
        lineReader.readLine("1,2000,Saturation,98%", storage);
        lineReader.readLine("2,3000,Alert,triggered", storage);
        List<PatientRecord> patientOneRecords = storage.getRecords(1, 0, 4000);
        List<PatientRecord> patientTwoRecords = storage.getRecords(2, 0, 4000);

        assertEquals(2, patientOneRecords.size());
        assertEquals(1, patientTwoRecords.size());
        assertEquals("ECG", patientOneRecords.get(0).getRecordType());
        assertEquals("Saturation", patientOneRecords.get(1).getRecordType());
        assertEquals("Alert", patientTwoRecords.get(0).getRecordType());
    }
}
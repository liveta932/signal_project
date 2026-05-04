package data_management;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RealTimeDataIntegrationTest {

    private DataStorage storage;
    private DataLineReader lineReader;

    @BeforeEach
    public void setUp() {
        storage = DataStorage.getInstance();
        storage.clear();
        lineReader = new DataLineReader();
    }

    @Test
    public void testRealTimeDataIsStored() {
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
        assertEquals(1.0, patientTwoRecords.get(0).getMeasurementValue());
    }
}
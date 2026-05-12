package data_management;

import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests that patient data is stored and returned correctly.
 */

class DataStorageTest {

    /**
     * Tests that records can be added and retrieved from storage.
     */

    @Test
    void testAddAndGetRecords() {
        DataStorage storage = DataStorage.getInstance();
        storage.clear();
        storage.addPatientData(1, 100.0, "WhiteBloodCells", 1714376789050L);
        storage.addPatientData(1, 200.0, "WhiteBloodCells", 1714376789051L);
        List<PatientRecord> records = storage.getRecords(1, 1714376789050L, 1714376789051L);

        assertEquals(2, records.size());
        assertEquals(100.0, records.get(0).getMeasurementValue());
    }

    /**
     * Tests that real-time patient updates are stored in order.
     */

    @Test
    void testRealTimeUpdatesAreStored() {
        DataStorage storage = DataStorage.getInstance();
        storage.clear();
        storage.addPatientData(1, 0.5, "ECG", 1000L);
        storage.addPatientData(1, 0.6, "ECG", 2000L);
        storage.addPatientData(1, 98.0, "Saturation", 3000L);

        List<PatientRecord> records = storage.getRecords(1, 0, 4000);

        assertEquals(3, records.size());
        assertEquals("ECG", records.get(0).getRecordType());
        assertEquals("ECG", records.get(1).getRecordType());
        assertEquals("Saturation", records.get(2).getRecordType());
    }
}
package data_management;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests the Patient class. It checks if Patient can return records correctly between a given start time and end time.
 */
public class PatientTest {

    /**
     * Tests that getRecords returns only records inside the time range.
     */

    @Test
    public void testGetRecordsInsideTimeRange() {
        Patient patient = new Patient(1);

        patient.addRecord(100.0, "ECG", 1000L);
        patient.addRecord(200.0, "ECG", 2000L);
        patient.addRecord(300.0, "ECG", 3000L);

        List<PatientRecord> records = patient.getRecords(1500L, 2500L);

        assertEquals(1, records.size());
        assertEquals(200.0, records.get(0).getMeasurementValue(), 0.001);
        assertEquals("ECG", records.get(0).getRecordType());
    }

    /**
     * Tests that getRecords includes records exactly on the start and end time.
     */

    @Test
    public void testGetRecordsIncludesStartAndEndTime() {
        Patient patient = new Patient(1);

        patient.addRecord(100.0, "ECG", 1000L);
        patient.addRecord(200.0, "ECG", 2000L);
        patient.addRecord(300.0, "ECG", 3000L);

        List<PatientRecord> records = patient.getRecords(1000L, 3000L);

        assertEquals(3, records.size());
    }

    /**
     * Tests that getRecords returns an empty list when there are no records in the given time range.
     */

    @Test
    public void testGetRecordsReturnsEmptyListWhenNoRecordsMatch() {
        Patient patient = new Patient(1);

        patient.addRecord(100.0, "ECG", 1000L);
        patient.addRecord(200.0, "ECG", 2000L);

        List<PatientRecord> records = patient.getRecords(3000L, 4000L);

        assertEquals(0, records.size());
    }
}
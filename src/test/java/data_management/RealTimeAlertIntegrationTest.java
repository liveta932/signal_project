package data_management;

import com.alerts.AlertGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests that real-time data can create alerts through the alert generator.
 */

public class RealTimeAlertIntegrationTest {

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
     * Tests that real-time blood pressure data creates an alert.
     */

    @Test
    public void testRealTimeBloodPressureDataCreatesAlert() {
        lineReader.readLine("1,1000,SystolicPressure,181", storage);
        Patient patient = storage.getAllPatients().get(0);
        AlertGenerator alertGenerator = new AlertGenerator(storage);
        alertGenerator.evaluateData(patient);

        assertEquals(1, alertGenerator.getAlerts().size());
        assertTrue(alertGenerator.getAlerts().get(0).getCondition().contains("systolic"));
    }

    /**
     * Tests that real-time triggered data creates an alert.
     */

    @Test
    public void testRealTimeTriggeredDataCreatesAlert() {
        lineReader.readLine("1,1000,Alert,triggered", storage);
        Patient patient = storage.getAllPatients().get(0);
        AlertGenerator alertGenerator = new AlertGenerator(storage);
        alertGenerator.evaluateData(patient);

        assertEquals(1, alertGenerator.getAlerts().size());
        assertTrue(alertGenerator.getAlerts().get(0).getCondition().contains("triggered"));
    }
}
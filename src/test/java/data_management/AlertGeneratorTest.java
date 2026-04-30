package data_management;

import com.alerts.AlertGenerator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests the AlertGenerator class. These tests check that AlertGenerator can run the alert rules
 * on patient data and store the alerts that are created.
 */

public class AlertGeneratorTest {

    /**
     * Tests if AlertGenerator creates an alert for critical systolic pressure.
     */

    @Test
    public void testEvaluateDataCreatesCriticalBloodPressureAlert() {
        DataStorage dataStorage = new DataStorage();
        dataStorage.addPatientData(1, 181, "SystolicPressure", 1000L);
        Patient patient = dataStorage.getAllPatients().get(0);
        AlertGenerator alertGenerator = new AlertGenerator(dataStorage);
        alertGenerator.evaluateData(patient);

        assertEquals(1, alertGenerator.getAlerts().size());
        assertTrue(alertGenerator.getAlerts().get(0).getCondition().contains("systolic"));
    }

    /**
     * Tests if AlertGenerator creates an alert for low oxygen saturation.
     */

    @Test
    public void testEvaluateDataCreatesLowSaturationAlert() {
        DataStorage dataStorage = new DataStorage();
        dataStorage.addPatientData(1, 91, "Saturation", 1000L);
        Patient patient = dataStorage.getAllPatients().get(0);
        AlertGenerator alertGenerator = new AlertGenerator(dataStorage);
        alertGenerator.evaluateData(patient);

        assertEquals(1, alertGenerator.getAlerts().size());
        assertTrue(alertGenerator.getAlerts().get(0).getCondition().contains("saturation"));
    }

    /**
     * Tests that normal patient data does not create an alert.
     */

    @Test
    public void testEvaluateDataWithNormalDataCreatesNoAlert() {
        DataStorage dataStorage = new DataStorage();

        dataStorage.addPatientData(1, 120, "SystolicPressure", 1000L);
        dataStorage.addPatientData(1, 80, "DiastolicPressure", 2000L);
        dataStorage.addPatientData(1, 98, "Saturation", 3000L);

        Patient patient = dataStorage.getAllPatients().get(0);
        AlertGenerator alertGenerator = new AlertGenerator(dataStorage);
        alertGenerator.evaluateData(patient);

        assertEquals(0, alertGenerator.getAlerts().size());
    }
}
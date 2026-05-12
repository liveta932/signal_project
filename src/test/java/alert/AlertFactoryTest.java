package alert;

import com.alerts.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests that alert factories create alerts with the correct values.
 */

public class AlertFactoryTest {

    /**
     * Tests that the blood pressure alert factory creates the correct alert.
     */

    @Test
    public void testBloodPressureAlertFactoryCreatesAlert() {
        AlertFactory factory = new BloodPressureAlertFactory();
        Alert basicAlert = factory.createAlert("1", "Critical systolic blood pressure", 1000L);

        assertEquals("1", basicAlert.getPatientId());
        assertEquals("Critical systolic blood pressure", basicAlert.getCondition());
        assertEquals(1000L, basicAlert.getTimestamp());
    }

    /**
     * Tests that the blood oxygen alert factory creates the correct alert.
     */

    @Test
    public void testBloodOxygenAlertFactoryCreatesAlert() {
        AlertFactory factory = new BloodOxygenAlertFactory();
        Alert basicAlert = factory.createAlert("2", "Low blood oxygen saturation", 2000L);

        assertEquals("2", basicAlert.getPatientId());
        assertEquals("Low blood oxygen saturation", basicAlert.getCondition());
        assertEquals(2000L, basicAlert.getTimestamp());
    }

    /**
     * Tests that the ECG alert factory creates the correct alert.
     */

    @Test
    public void testECGAlertFactoryCreatesAlert() {
        AlertFactory factory = new ECGAlertFactory();
        Alert basicAlert = factory.createAlert("3", "Abnormal ECG peak", 3000L);

        assertEquals("3", basicAlert.getPatientId());
        assertEquals("Abnormal ECG peak", basicAlert.getCondition());
        assertEquals(3000L, basicAlert.getTimestamp());
    }

    /**
     * Tests that the triggered alert factory creates the correct alert.
     */

    @Test
    public void testTriggeredAlertFactoryCreatesAlert() {
        AlertFactory factory = new TriggeredAlertFactory();
        Alert basicAlert = factory.createAlert("4", "Patient/nurse triggered alert", 4000L);

        assertEquals("4", basicAlert.getPatientId());
        assertEquals("Patient/nurse triggered alert", basicAlert.getCondition());
        assertEquals(4000L, basicAlert.getTimestamp());
    }
}
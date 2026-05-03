package data_management;

import com.alerts.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AlertDecoratorTest {

    @Test
    public void testBasicAlertStoresValues() {
        Alert alert = new BasicAlert("1", "Low blood oxygen saturation", 1000L);

        assertEquals("1", alert.getPatientId());
        assertEquals("Low blood oxygen saturation", alert.getCondition());
        assertEquals(1000L, alert.getTimestamp());
    }

    @Test
    public void testPriorityAlertDecoratorAddsPriority() {
        Alert alert = new BasicAlert("1", "Critical systolic blood pressure", 1000L);
        Alert decoratedAlert = new PriorityAlertDecorator(alert, "HIGH");

        assertEquals("1", decoratedAlert.getPatientId());
        assertTrue(decoratedAlert.getCondition().contains("Priority: HIGH"));
        assertTrue(decoratedAlert.getCondition().contains("Critical systolic blood pressure"));
        assertEquals(1000L, decoratedAlert.getTimestamp());
    }

    @Test
    public void testRepeatedAlertDecoratorAddsRepeatInformation() {
        Alert alert = new BasicAlert("1", "Patient/nurse triggered alert", 1000L);
        Alert decoratedAlert = new RepeatedAlertDecorator(alert, 3, 60000);

        assertEquals("1", decoratedAlert.getPatientId());
        assertTrue(decoratedAlert.getCondition().contains("Patient/nurse triggered alert"));
        assertTrue(decoratedAlert.getCondition().contains("Repeated alert"));
        assertTrue(decoratedAlert.getCondition().contains("3"));
        assertTrue(decoratedAlert.getCondition().contains("60000"));
        assertEquals(1000L, decoratedAlert.getTimestamp());
    }

    @Test
    public void testDecoratorsCanBeCombined() {
        Alert alert = new BasicAlert("1", "Critical systolic blood pressure", 1000L);
        Alert decoratedAlert = new PriorityAlertDecorator(alert, "HIGH");
        decoratedAlert = new RepeatedAlertDecorator(decoratedAlert, 3, 60000);

        assertEquals("1", decoratedAlert.getPatientId());
        assertTrue(decoratedAlert.getCondition().contains("Priority: HIGH"));
        assertTrue(decoratedAlert.getCondition().contains("Repeated alert"));
        assertTrue(decoratedAlert.getCondition().contains("Critical systolic blood pressure"));
        assertEquals(1000L, decoratedAlert.getTimestamp());
    }

    @Test
    public void testAlertGeneratorAppliesPriorityDecorator() {
        DataStorage dataStorage = DataStorage.getInstance();
        dataStorage.clear();
        dataStorage.addPatientData(1, 181, "SystolicPressure", 1000L);
        Patient patient = dataStorage.getAllPatients().get(0);
        AlertGenerator alertGenerator = new AlertGenerator(dataStorage);
        alertGenerator.evaluateData(patient);

        assertEquals(1, alertGenerator.getAlerts().size());
        assertTrue(alertGenerator.getAlerts().get(0).getCondition().contains("Priority: HIGH"));
        assertTrue(alertGenerator.getAlerts().get(0).getCondition().contains("Critical systolic blood pressure"));
    }

    @Test
    public void testAlertGeneratorAppliesRepeatedDecorator() {
        DataStorage dataStorage = DataStorage.getInstance();
        dataStorage.clear();
        dataStorage.addPatientData(1, 100, "SystolicPressure", 1000L);
        dataStorage.addPatientData(1, 112, "SystolicPressure", 2000L);
        dataStorage.addPatientData(1, 125, "SystolicPressure", 3000L);
        Patient patient = dataStorage.getAllPatients().get(0);
        AlertGenerator alertGenerator = new AlertGenerator(dataStorage);
        alertGenerator.evaluateData(patient);

        assertEquals(1, alertGenerator.getAlerts().size());
        assertTrue(alertGenerator.getAlerts().get(0).getCondition().contains("Repeated alert"));
        assertTrue(alertGenerator.getAlerts().get(0).getCondition().contains("Increasing trend"));
    }
}
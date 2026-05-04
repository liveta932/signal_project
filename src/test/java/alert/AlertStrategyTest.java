package alert;

import com.alerts.*;
import data_management.PatientRecord;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AlertStrategyTest {

    @Test
    public void testBloodPressureStrategyCreatesCriticalPressureAlert() {
        AlertStrategy strategy = new BloodPressureStrategy();
        List<PatientRecord> records = List.of(new PatientRecord(1, 181, "SystolicPressure", 1000L));
        List<Alert> alerts = strategy.checkAlert(records);

        assertEquals(1, alerts.size());
        assertTrue(alerts.get(0).getCondition().contains("systolic"));
    }

    @Test
    public void testBloodPressureStrategyCreatesTrendAlert() {
        AlertStrategy strategy = new BloodPressureStrategy();
        List<PatientRecord> records = List.of(
                new PatientRecord(1, 100, "SystolicPressure", 1000L),
                new PatientRecord(1, 112, "SystolicPressure", 2000L),
                new PatientRecord(1, 125, "SystolicPressure", 3000L)
        );
        List<Alert> alerts = strategy.checkAlert(records);

        assertEquals(1, alerts.size());
        assertTrue(alerts.get(0).getCondition().contains("Increasing"));
    }

    @Test
    public void testOxygenSaturationStrategyCreatesLowSaturationAlert() {
        AlertStrategy strategy = new OxygenSaturationStrategy();
        List<PatientRecord> records = List.of(new PatientRecord(1, 91, "Saturation", 1000L));
        List<Alert> alerts = strategy.checkAlert(records);

        assertEquals(1, alerts.size());
        assertTrue(alerts.get(0).getCondition().contains("saturation"));
    }

    @Test
    public void testHeartRateStrategyCreatesEcgAlert() {
        AlertStrategy strategy = new HeartRateStrategy();
        List<PatientRecord> records = List.of(
                new PatientRecord(1, 0.1, "ECG", 1000L),
                new PatientRecord(1, 0.2, "ECG", 2000L),
                new PatientRecord(1, 2.0, "ECG", 3000L)
        );
        List<Alert> alerts = strategy.checkAlert(records);

        assertEquals(1, alerts.size());
        assertTrue(alerts.get(0).getCondition().contains("ECG"));
    }

}
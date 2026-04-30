package data_management;

import com.alerts.*;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests the different alert rule classes.
 */

public class AlertRulesTest {

    /**
     * Tests if a critical systolic blood pressure alert is created
     * when systolic pressure is above 180.
     */
    @Test
    public void testCriticalSystolicPressureAlert() {
        CriticalBloodPressureRule rule = new CriticalBloodPressureRule();

        List<PatientRecord> records = List.of(
                new PatientRecord(1, 181, "SystolicPressure", 1000L)
        );

        List<Alert> alerts = rule.check(records);

        assertEquals(1, alerts.size());
        assertEquals("1", alerts.get(0).getPatientId());
        assertTrue(alerts.get(0).getCondition().contains("systolic"));
    }

    /**
     * Tests if a critical diastolic blood pressure alert is createdcwhen diastolic pressure is below 60.
     */

    @Test
    public void testCriticalDiastolicPressureAlert() {
        CriticalBloodPressureRule rule = new CriticalBloodPressureRule();

        List<PatientRecord> records = List.of(
                new PatientRecord(1, 55, "DiastolicPressure", 1000L)
        );

        List<Alert> alerts = rule.check(records);

        assertEquals(1, alerts.size());
        assertEquals("1", alerts.get(0).getPatientId());
        assertTrue(alerts.get(0).getCondition().contains("diastolic"));
    }

    /**
     * Tests if no blood pressure alert is created for normal values.
     */

    @Test
    public void testNormalBloodPressureDoesNotTriggerAlert() {
        CriticalBloodPressureRule rule = new CriticalBloodPressureRule();

        List<PatientRecord> records = List.of(
                new PatientRecord(1, 120, "SystolicPressure", 1000L),
                new PatientRecord(1, 80, "DiastolicPressure", 1000L)
        );

        List<Alert> alerts = rule.check(records);

        assertEquals(0, alerts.size());
    }

    /**
     * Tests if an increasing blood pressure trend creates an alert.
     *
     * The values increase by more than 10 each time.
     */

    @Test
    public void testIncreasingBloodPressureTrend() {
        BloodPressureTrendRule rule = new BloodPressureTrendRule();

        List<PatientRecord> records = List.of(
                new PatientRecord(1, 100, "SystolicPressure", 1000L),
                new PatientRecord(1, 112, "SystolicPressure", 2000L),
                new PatientRecord(1, 125, "SystolicPressure", 3000L)
        );

        List<Alert> alerts = rule.check(records);

        assertEquals(1, alerts.size());
        assertTrue(alerts.get(0).getCondition().contains("Increasing"));
    }

    /**
     * Tests if a decreasing blood pressure trend creates an alert.
     *
     * The values decrease by more than 10 each time.
     */

    @Test
    public void testDecreasingBloodPressureTrend() {
        BloodPressureTrendRule rule = new BloodPressureTrendRule();

        List<PatientRecord> records = List.of(
                new PatientRecord(1, 130, "SystolicPressure", 1000L),
                new PatientRecord(1, 118, "SystolicPressure", 2000L),
                new PatientRecord(1, 105, "SystolicPressure", 3000L)
        );

        List<Alert> alerts = rule.check(records);

        assertEquals(1, alerts.size());
        assertTrue(alerts.get(0).getCondition().contains("Decreasing"));
    }

    /**
     * Tests if low oxygen saturation creates an alert.
     */

    @Test
    public void testLowSaturationAlert() {
        LowSaturationRule rule = new LowSaturationRule();

        List<PatientRecord> records = List.of(
                new PatientRecord(1, 91, "Saturation", 1000L)
        );

        List<Alert> alerts = rule.check(records);

        assertEquals(1, alerts.size());
        assertTrue(alerts.get(0).getCondition().contains("saturation"));
    }

    /**
     * Tests if a rapid saturation drop creates an alert.
     *
     * The saturation drops by 5 within 10 minutes.
     */

    @Test
    public void testRapidSaturationDropAlert() {
        RapidSaturationDropRule rule = new RapidSaturationDropRule();

        List<PatientRecord> records = List.of(
                new PatientRecord(1, 98, "Saturation", 1000L),
                new PatientRecord(1, 93, "Saturation", 2000L)
        );

        List<Alert> alerts = rule.check(records);

        assertEquals(1, alerts.size());
        assertTrue(alerts.get(0).getCondition().contains("Rapid"));
    }

    /**
     * Tests the combined hypotensive hypoxemia alert.
     *
     * The alert should be created when systolic pressure is below 90
     * and saturation is below 92.
     */

    @Test
    public void testHypotensiveHypoxemiaAlert() {
        HypotensiveHypoxemiaRule rule = new HypotensiveHypoxemiaRule();

        List<PatientRecord> records = List.of(
                new PatientRecord(1, 85, "SystolicPressure", 1000L),
                new PatientRecord(1, 91, "Saturation", 2000L)
        );

        List<Alert> alerts = rule.check(records);

        assertEquals(1, alerts.size());
        assertTrue(alerts.get(0).getCondition().contains("Hypotensive"));
    }

    /**
     * Tests if an abnormal ECG peak creates an alert.
     *
     * The value 2.0 is much higher than the other ECG values.
     */

    @Test
    public void testAbnormalEcgAlert() {
        AbnormalEcgRule rule = new AbnormalEcgRule();

        List<PatientRecord> records = List.of(
                new PatientRecord(1, 0.1, "ECG", 1000L),
                new PatientRecord(1, 0.2, "ECG", 2000L),
                new PatientRecord(1, 2.0, "ECG", 3000L)
        );

        List<Alert> alerts = rule.check(records);

        assertEquals(1, alerts.size());
        assertTrue(alerts.get(0).getCondition().contains("ECG"));
    }
}
package data_management;

import com.alerts.Alert;
import com.alerts.TriggeredAlertRule;
import data_management.PatientRecord;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests the TriggeredAlertRule class. This rule checks alerts that were manually triggered by a patient or nurse.
 *
 * In FileDataReader:
 * - "triggered" is saved as 1.0
 * - "resolved" is saved as 0.0
 */

public class TriggeredAlertRuleTest {

    /**
     * Tests that a triggered alert creates one Alert object.
     */

    @Test
    public void testTriggeredAlertCreatesAlert() {
        TriggeredAlertRule rule = new TriggeredAlertRule();

        List<PatientRecord> records = List.of(
                new PatientRecord(1, 1.0, "Alert", 1000L)
        );

        List<Alert> alerts = rule.check(records);

        assertEquals(1, alerts.size());
        assertEquals("1", alerts.get(0).getPatientId());
        assertTrue(alerts.get(0).getCondition().contains("triggered"));
        assertEquals(1000L, alerts.get(0).getTimestamp());
    }

    /**
     * Tests that a resolved alert does not create an Alert object.
     */

    @Test
    public void testResolvedAlertDoesNotCreateAlert() {
        TriggeredAlertRule rule = new TriggeredAlertRule();

        List<PatientRecord> records = List.of(
                new PatientRecord(1, 0.0, "Alert", 1000L)
        );

        List<Alert> alerts = rule.check(records);

        assertEquals(0, alerts.size());
    }

    /**
     * Tests that other record types do not create triggered alerts.
     */

    @Test
    public void testOtherRecordTypeDoesNotCreateAlert() {
        TriggeredAlertRule rule = new TriggeredAlertRule();

        List<PatientRecord> records = List.of(
                new PatientRecord(1, 1.0, "Saturation", 1000L)
        );

        List<Alert> alerts = rule.check(records);

        assertEquals(0, alerts.size());
    }

    /**
     * Tests that multiple triggered alerts create multiple Alert objects.
     */

    @Test
    public void testMultipleTriggeredAlertsCreateMultipleAlerts() {
        TriggeredAlertRule rule = new TriggeredAlertRule();

        List<PatientRecord> records = List.of(
                new PatientRecord(1, 1.0, "Alert", 1000L),
                new PatientRecord(2, 1.0, "Alert", 2000L)
        );

        List<Alert> alerts = rule.check(records);

        assertEquals(2, alerts.size());
        assertEquals("1", alerts.get(0).getPatientId());
        assertEquals(1000L, alerts.get(0).getTimestamp());
        assertEquals("2", alerts.get(1).getPatientId());
        assertEquals(2000L, alerts.get(1).getTimestamp());
    }

    /**
     * Tests that an empty list creates no alerts.
     */

    @Test
    public void testEmptyRecordsCreateNoAlerts() {
        TriggeredAlertRule rule = new TriggeredAlertRule();
        List<PatientRecord> records = List.of();
        List<Alert> alerts = rule.check(records);

        assertEquals(0, alerts.size());
    }
}
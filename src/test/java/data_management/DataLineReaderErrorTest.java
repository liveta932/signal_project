package data_management;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests that bad data lines do not add records to storage.
 */

public class DataLineReaderErrorTest {

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
     * Tests that a badly formatted line does not add a record.
     */

    @Test
    public void testBadFormatDoesNotAddRecord() {
        lineReader.readLine("bad message", storage);

        assertEquals(0, storage.getAllPatients().size());
    }

    /**
     * Tests that a line with missing data does not add a record.
     */

    @Test
    public void testMissingDataDoesNotAddRecord() {
        lineReader.readLine("1,1000,ECG", storage);

        assertEquals(0, storage.getAllPatients().size());
    }

    /**
     * Tests that a line with a wrong patient ID does not add a record.
     */

    @Test
    public void testWrongNumberDoesNotAddRecord() {
        lineReader.readLine("one,1000,ECG,0.5", storage);

        assertEquals(0, storage.getAllPatients().size());
    }

    /**
     * Tests that a line with a wrong value does not add a record.
     */

    @Test
    public void testWrongValueDoesNotAddRecord() {
        lineReader.readLine("1,1000,ECG,abc", storage);

        assertEquals(0, storage.getAllPatients().size());
    }

    /**
     * Tests that a line with a wrong timestamp does not add a record.
     */

    @Test
    public void testWrongTimestampDoesNotAddRecord() {
        lineReader.readLine("1,wrong,ECG,0.5", storage);

        assertEquals(0, storage.getAllPatients().size());
    }
}
package data_management;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DataLineReaderErrorTest {

    private DataStorage storage;
    private DataLineReader lineReader;

    @BeforeEach
    public void setUp() {
        storage = DataStorage.getInstance();
        storage.clear();
        lineReader = new DataLineReader();
    }

    @Test
    public void testBadFormatDoesNotAddRecord() {
        lineReader.readLine("bad message", storage);

        assertEquals(0, storage.getAllPatients().size());
    }

    @Test
    public void testMissingDataDoesNotAddRecord() {
        lineReader.readLine("1,1000,ECG", storage);

        assertEquals(0, storage.getAllPatients().size());
    }

    @Test
    public void testWrongNumberDoesNotAddRecord() {
        lineReader.readLine("one,1000,ECG,0.5", storage);

        assertEquals(0, storage.getAllPatients().size());
    }

    @Test
    public void testWrongValueDoesNotAddRecord() {
        lineReader.readLine("1,1000,ECG,abc", storage);

        assertEquals(0, storage.getAllPatients().size());
    }

    @Test
    public void testWrongTimestampDoesNotAddRecord() {
        lineReader.readLine("1,wrong,ECG,0.5", storage);

        assertEquals(0, storage.getAllPatients().size());
    }
}
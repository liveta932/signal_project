package data_management;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class WebSocketDataReaderTest {

    private DataStorage storage;

    @BeforeEach
    public void setUp() {
        storage = DataStorage.getInstance();
        storage.clear();
    }

    @Test
    public void testInvalidWebSocketAddressThrowsException() {
        WebSocketDataReader reader = new WebSocketDataReader("wrong address");

        assertThrows(IOException.class, () -> {
            reader.readData(storage);
        });
    }
}
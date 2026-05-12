package data_management;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Tests websocket data reader error handling.
 */

public class WebSocketDataReaderTest {

    private DataStorage storage;

    /**
     * Sets up clean storage before each test.
     */

    @BeforeEach
    public void setUp() {
        storage = DataStorage.getInstance();
        storage.clear();
    }

    /**
     * Tests that an invalid websocket address throws an exception.
     */

    @Test
    public void testInvalidWebSocketAddressThrowsException() {
        WebSocketDataReader reader = new WebSocketDataReader("wrong address");

        assertThrows(IOException.class, () -> {
            reader.readData(storage);
        });
    }
}
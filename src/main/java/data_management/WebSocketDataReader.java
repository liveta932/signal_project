package data_management;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Reads patient data from a WebSocket server and stores it in DataStorage.
 */


public class WebSocketDataReader implements DataReader {

    private final String serverAdress;
    private WebSocketClient client;
    private final DataLineReader lineReader = new DataLineReader();

    public WebSocketDataReader(String serverAdress) {
        this.serverAdress = serverAdress;
    }

    /**
     * Connects to the WebSocket server and starts reading real-time data.
     * @param  dataStorage the storage where patient data will be saved
     * @throws IOException if WebSocket address is invalid
     */

    @Override
    public void readData(DataStorage dataStorage) throws IOException {
        try {
            URI serverURI = new URI(serverAdress);
            client = new WebSocketClient(serverURI) {
                @Override
                public void onOpen(ServerHandshake serverHandshake) {
                    System.out.println("Connected to " + serverAdress);
                }

                @Override
                public void onMessage(String message) {
                    try {
                        lineReader.readLine(message, dataStorage);
                        System.out.println("Received data: " + message);
                    } catch (Exception e) {
                        System.err.println("Could not process WebSocket message: " + message);
                        System.err.println(e.getMessage());
                    }
                }

                @Override
                public void onClose(int i, String s, boolean b) {
                    System.out.println("WebSocket connection closed.");
                    System.out.println("Code: " + i);
                    System.out.println("Reason: " + s);
                }

                @Override
                public void onError(Exception e) {
                    System.err.println("Error: " + e.getMessage());
                }
            };
            client.connect();
        } catch (URISyntaxException e){
            throw new IOException("Invalid Websocket address: " + serverAdress, e);

        }
    }

    /**
     * Stops the Websocket reader.
     * @throws IOException if the WebSocket fails to be closed
     */

    @Override
    public void stop() throws IOException {
        if (client != null) {
            client.close();
        }
    }
}

package com.cardio_generator.outputs;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executors;

/**
 * Sends generated patient data over a TCP connection.
 * This class works as one possible output strategy in the simulator. It starts a TCP server, waits for a client to connect, and then sends
 * generated data messages to that client. This allows the simulator to stream data to another system instead of only printing it to the console
 * or saving it to a file.
 */
public class TcpOutputStrategy implements OutputStrategy {

    private ServerSocket serverSocket;
    private Socket clientSocket;
    private PrintWriter out;

    /**
     * Creates a TCP output strategy and starts the server.
     * A server socket is opened on the given port. The client connection is accepted in a separate thread so the simulator does not stop while
     * waiting for a client.
     * @param port port number used for the TCP server
     * @throws IOException if the server socket cannot be opened
     */
    public TcpOutputStrategy(int port) {
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("TCP Server started on port " + port);

            // Accept clients in a new thread to not block the main thread
            Executors.newSingleThreadExecutor().submit(() -> {
                try {
                    clientSocket = serverSocket.accept();
                    out = new PrintWriter(clientSocket.getOutputStream(), true);
                    System.out.println("Client connected: " + clientSocket.getInetAddress());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sends one generated data message to the connected client.
     * The message is formatted as a comma-separated line containing the patient ID, timestamp, label, and value. If no client is connected yet, nothing is sent.
     * @param patientId ID of the patient
     * @param timestamp time when the data was generated
     * @param label type of generated data, for example ECG or Saturation
     * @param data generated value to send
     */

    @Override
    public void output(int patientId, long timestamp, String label, String data) {
        if (out != null) {
            String message = String.format("%d,%d,%s,%s", patientId, timestamp, label, data);
            out.println(message);
        }
    }
}

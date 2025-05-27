import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        int port = 12345;
        try (ServerSocket serverSocket = new ServerSocket(port)) { // cite: 88
            System.out.println("Server listening on port " + port);

            Socket clientSocket = serverSocket.accept(); // Accept client connections [cite: 89]
            System.out.println("Client connected: " + clientSocket.getInetAddress());

            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())); // cite: 89
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true); // cite: 89

            String clientMessage;
            while ((clientMessage = in.readLine()) != null) { // cite: 89
                System.out.println("Client: " + clientMessage);
                if (clientMessage.equalsIgnoreCase("bye")) {
                    System.out.println("Server closing connection.");
                    break;
                }
                out.println("Server received: " + clientMessage); // Two-way communication [cite: 89]
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
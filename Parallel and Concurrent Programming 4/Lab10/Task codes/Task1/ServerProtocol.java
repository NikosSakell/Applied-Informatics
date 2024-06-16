import java.net.*;
import java.io.*;
import java.util.Map;

public class ServerProtocol {

	public void processRequest(String theInput, Map<Integer, Socket> connections) { // removed senderId
		System.out.println("Received message from a client: " + theInput);

		broadcastMessage(theInput, connections, -1); 
	}

	public void broadcastMessage(String message, Map<Integer, Socket> connections, int senderId) {
        for (Map.Entry<Integer, Socket> entry : connections.entrySet()) {
            int recipientId = entry.getKey();
            Socket recipientSocket = entry.getValue();

            try {
                PrintWriter recipientOut = new PrintWriter(recipientSocket.getOutputStream(), true);
                if (senderId == -1 || recipientId == senderId) {
                    recipientOut.println(message);
                }
            } catch (IOException e) {
                System.out.println("Error sending message to client " + recipientId);
            }
        }
    }

}

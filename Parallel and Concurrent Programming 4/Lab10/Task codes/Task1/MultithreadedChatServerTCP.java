import java.net.*;
import java.io.*;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MultithreadedChatServerTCP {
    private static final int PORT = 1234;

    public static void main(String args[]) throws IOException {
        ServerSocket connectionSocket = new ServerSocket(PORT);

        Map<Integer, Socket> connections = new ConcurrentHashMap<>(); 
        int clientIdCounter = 0; 

        while (true) {

            System.out.println("Server is waiting for a client on port: " + PORT);
            Socket dataSocket = connectionSocket.accept();

// 			System.out.println("Server is waiting first client in port: " + PORT);
// 			Socket dataSocket1 = connectionSocket.accept();

            System.out.println("Received request from " + dataSocket.getInetAddress());

// 			System.out.println("Received request from " + dataSocket1.getInetAddress());
//			Μέχρι στιγμής δεν αλλάζει κάτι με τον παλιό κ΄ώδικα

//			System.out.println("Server is waiting second client in port: " + PORT);
// 			Socket dataSocket2 = connectionSocket.accept();
// 			System.out.println("Received request from " + dataSocket2.getInetAddress());

            connections.put(clientIdCounter, dataSocket); // Προσθήκη στα connections του socket 
            ServerThread sthread = new ServerThread(connections, clientIdCounter); 
            sthread.start();

// 			ServerThread sthread1 = new ServerThread(dataSocket1, dataSocket2);
// 			sthread1.start();
// 			ServerThread sthread2 = new ServerThread(dataSocket2, dataSocket1);
// 			sthread2.start();

            clientIdCounter++;
//			Αυξάνουμε τον client counter

        }
    }
}



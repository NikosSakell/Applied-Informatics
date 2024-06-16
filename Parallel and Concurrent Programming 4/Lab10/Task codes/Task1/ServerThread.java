import java.io.*;
import java.net.*;
import java.util.Map;

class ServerThread extends Thread {
    private Map<Integer, Socket> connections;  // Map for active connections
    private int clientId;                      // ID of the client this thread handles

    private BufferedReader in;
    private PrintWriter out;

    private static final String EXIT = "CLOSE";

    public ServerThread(Map<Integer, Socket> connections, int clientId) {
        this.connections = connections;
        this.clientId = clientId;

        try {
            Socket clientSocket = connections.get(clientId); // Get client socket
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out = new PrintWriter(clientSocket.getOutputStream(), true);
        } catch (IOException e) {
            System.out.println("I/O Error " + e);
        }
    }

	public void run() {

		ServerProtocol app = new ServerProtocol();
		String inmsg;
		
		try {
			System.out.println("We are currently in thread: " + Thread.currentThread());
			inmsg = in.readLine();
			while (!inmsg.equals(EXIT)) {
				app.broadcastMessage(inmsg, connections, -1); // Broadcast the message to all clients 
				inmsg = in.readLine(); 
			}
	
			connections.remove(clientId);
			System.out.println("Data socket closed for client " + clientId);
		} catch (IOException e) {
			System.out.println("I/O Error " + e);
		}
	}

}

		

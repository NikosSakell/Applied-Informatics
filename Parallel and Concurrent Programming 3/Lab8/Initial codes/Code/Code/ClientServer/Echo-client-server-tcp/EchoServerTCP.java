import java.net.*;
import java.io.*;

public class EchoServerTCP {
	private static final int PORT = 1234;
	private static final String EXIT = "CLOSE";

	public static void main(String args[]) throws IOException {

		ServerSocket connectionSocket = new ServerSocket(PORT);
		System.out.println("Server is listening to port: " + PORT);

		Socket dataSocket = connectionSocket.accept();
		System.out.println("Received request from " + dataSocket.getInetAddress());

		InputStream is = dataSocket.getInputStream();
		BufferedReader in = new BufferedReader(new InputStreamReader(is));
		OutputStream os = dataSocket.getOutputStream();
		PrintWriter out = new PrintWriter(os,true);
		
		String inmsg, outmsg;
        ServerProtocol app = new ServerProtocol();

        while (true) {
            inmsg = in.readLine();
            System.out.println("Received message: " + inmsg); // debug line
            if (inmsg == null || inmsg.equals("CLOSE")) {
                out.println("CLOSE"); // Echo back the CLOSE message
                break;
            }
            
            outmsg = app.processRequest(inmsg);
            out.println(outmsg); 
        }

		dataSocket.close();
		System.out.println("Data socket closed");	

		
	}
}			


//Console Results:

// java EchoServerTCP.java
// Server is listening to port: 1234
// Received request from /127.0.0.1
// Received message: + 5 8
// Received message: - 5 8
// Received message: * 8 2
// Received message: / 10 5
// Received message: / 10 3
// Received message: / 10 0
// Received message: null
// Data socket closed


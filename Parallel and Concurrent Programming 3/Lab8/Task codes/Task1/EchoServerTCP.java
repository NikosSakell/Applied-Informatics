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

		//Set input and output streams
		InputStream is = dataSocket.getInputStream();
		BufferedReader in = new BufferedReader(new InputStreamReader(is));
		OutputStream os = dataSocket.getOutputStream();
		PrintWriter out = new PrintWriter(os,true);
		
		//Receive request message, procces it and send reply back
		String inmsg, outmsg;

        ServerProtocol app = new ServerProtocol();

        while (true) {
            inmsg = in.readLine();
            
            if (inmsg.equals("CLOSE")) { // Check for "CLOSE" or null
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
// Received message from client: Nikos,1
// Send message to client: NIKOS
// Received message from client: Nikos,2
// Send message to client: nikos
// Received message from client: Nikos Sak,3,5
// Send message to client: Snptx Xfp
// Received message from client: Snptx Xfp,4,5
// Send message to client: Nikos Sak
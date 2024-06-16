import java.net.*;
import java.io.*;

public class EchoClientTCP {
	private static final String HOST = "localhost";
	private static final int PORT = 1234;
	private static final String EXIT = "CLOSE";

	public static void main(String args[]) throws IOException {

		Socket dataSocket = new Socket(HOST, PORT);
		
		InputStream is = dataSocket.getInputStream();
		BufferedReader in = new BufferedReader(new InputStreamReader(is));
		OutputStream os = dataSocket.getOutputStream();
		PrintWriter out = new PrintWriter(os,true);
		       	
		System.out.println("Connection to " + HOST + " established");

		String inmsg, outmsg;
        ClientProtocol app = new ClientProtocol();

        outmsg = app.prepareRequest(); 
        while (!outmsg.equals("CLOSE")) {
            out.println(outmsg);
            inmsg = in.readLine(); 
            System.out.println("Received message: " + inmsg); // debug line
            if (inmsg == null || inmsg.equals("CLOSE")) {
                break;
            }

            app.processReply(inmsg);
            outmsg = app.prepareRequest(); 
        }

		dataSocket.close();
		System.out.println("Data Socket closed");

	}
}			


//Console Results:

// java EchoClientTCP.java
// Connection to localhost established
// Enter operation (+, -, *, /) and two numbers (e.g., + 5 10): + 5 8
// Received message: R 13
// Result: 13
// Enter operation (+, -, *, /) and two numbers (e.g., + 5 10): - 5 8
// Received message: R -3
// Result: -3
// Enter operation (+, -, *, /) and two numbers (e.g., + 5 10): * 8 2
// Received message: R 16
// Result: 16
// Enter operation (+, -, *, /) and two numbers (e.g., + 5 10): / 10 5
// Received message: R 2
// Result: 2
// Enter operation (+, -, *, /) and two numbers (e.g., + 5 10): / 10 3
// Received message: R 3
// Result: 3
// Enter operation (+, -, *, /) and two numbers (e.g., + 5 10): / 10 0
// Received message: E Division by zero
// Error: Division by zero
// Enter operation (+, -, *, /) and two numbers (e.g., + 5 10): CLOSE
// Data Socket closed
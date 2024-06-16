import java.net.*;
import java.io.*;
public class EchoClientTCP {
	private static final String HOST = "localhost";
	private static final int PORT = 1234;
	private static final String EXIT = "CLOSE";

	public static void main(String args[]) throws IOException {

		//Create Socket();
		Socket dataSocket = new Socket(HOST, PORT);
		
		//Connection
		InputStream is = dataSocket.getInputStream();
		BufferedReader in = new BufferedReader(new InputStreamReader(is));
		OutputStream os = dataSocket.getOutputStream();
		PrintWriter out = new PrintWriter(os,true);
		       	
		System.out.println("Connection to " + HOST + " established");

		String inmsg, outmsg;
        ClientProtocol app = new ClientProtocol();

        outmsg = app.prepareRequest(); // Get the initial request

        while (!outmsg.equals("CLOSE")) {
            out.println(outmsg); // Send request to server

            // Wait for the server's response before sending the next request
            inmsg = in.readLine(); 
            if (inmsg == null) {        
                break;                 
            }
            app.processReply(inmsg);

            outmsg = app.prepareRequest(); // Get the next request
        }
        
        // Send CLOSE message to the server if loop was broken due to client input
        

        dataSocket.close();
        System.out.println("Data Socket closed");
	}
}			

// Console Results:

// java EchoClientTCP.java

// Connection to localhost established
// Enter message to send to server: Nikos
// Enter 1 for UPPERCASE, 2 for lowercase, 3 for message Ciphering, 4 for Deciphering: 1    
// Message received from server: NIKOS
// Enter message to send to server: Nikos
// Enter 1 for UPPERCASE, 2 for lowercase, 3 for message Ciphering, 4 for Deciphering: 2    
// Message received from server: nikos
// Enter message to send to server: Nikos Sak
// Enter 1 for UPPERCASE, 2 for lowercase, 3 for message Ciphering, 4 for Deciphering: 3    
// Enter offset: 5
// Message received from server: Snptx Xfp
// Enter message to send to server: Snptx Xfp
// Enter 1 for UPPERCASE, 2 for lowercase, 3 for message Ciphering, 4 for Deciphering: 4    
// Enter offset: 5
// Message received from server: Nikos Sak
// Enter message to send to server: CLOSE
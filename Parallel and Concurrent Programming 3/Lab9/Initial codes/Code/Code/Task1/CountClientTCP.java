import java.net.*;
import java.io.*;

public class CountClientTCP {
	private static final String HOST = "localhost";
	private static final int PORT = 1234;
	
	public static void main(String args[]) throws IOException {

		Socket dataSocket = new Socket(HOST, PORT);
		
		InputStream is = dataSocket.getInputStream();
		BufferedReader in = new BufferedReader(new InputStreamReader(is));
		OutputStream os = dataSocket.getOutputStream();
		PrintWriter out = new PrintWriter(os,true);
		       	
		String inmsg, outmsg;
		ClientProtocol app = new ClientProtocol();
		
		int iters = 10000;
		for (int i = 0; i < iters; i++)  {
	        outmsg = app.prepareRequest();
			out.println(outmsg);
			inmsg = in.readLine();

			System.out.println("Received message: " + inmsg); // debug line
            if (inmsg == null || inmsg.equals("CLOSE")) {
                break;
            }

			app.processReply(inmsg);
		}
		outmsg = app.prepareExit();
		out.println(outmsg);
		//inmsg = in.readLine();
	    //app.processReply(inmsg);
		
		dataSocket.close();
		System.out.println("Data Socket closed");
	}
}			


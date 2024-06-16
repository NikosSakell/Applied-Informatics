import java.io.*;
import java.net.*;

public class ServerThread extends Thread{

    private Socket dataSocket;
	private InputStream is;
   	private BufferedReader in;
	private OutputStream os;
   	private PrintWriter out;
	private static final String EXIT = "CLOSE";

    public ServerThread(Socket socket)
    {
        dataSocket = socket;
        try {
            is = dataSocket.getInputStream();
            in = new BufferedReader(new InputStreamReader(is));
            os = dataSocket.getOutputStream();
            out = new PrintWriter(os,true);
        }
        catch (IOException e)	{		
            System.out.println("I/O Error " + e);
        }
    }


    public void run(){

        String inmsg, outmsg;

        try{
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

        }catch (IOException e){
            System.out.println("I/O Error "+ e);
        }

    }
    
}

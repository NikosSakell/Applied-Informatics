import java.net.*;
import java.io.*;

public class MultiThreadEchoServerTCP {

	private static final int PORT = 1235;
	private static final String EXIT = "CLOSE";

	public static void main(String args[]) throws IOException {

		

        while(true){

            ServerSocket connectionSocket = new ServerSocket(PORT);
            System.out.println("Server is listening to port: " + PORT);
    
            Socket dataSocket = connectionSocket.accept();
            System.out.println("Received request from " + dataSocket.getInetAddress());
            
            ServerThread sthread = new ServerThread(dataSocket);
            sthread.start();
        }

	}
}		
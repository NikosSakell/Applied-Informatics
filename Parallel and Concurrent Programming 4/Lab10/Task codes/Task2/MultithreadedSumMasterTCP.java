import java.net.*;
import java.io.*;

public class MultithreadedSumMasterTCP {
	private static final int PORT = 1234;
	private static final int numWorkers = Runtime.getRuntime().availableProcessors();
	public static Sum n = new Sum(100000);


	public static void main(String args[]) throws IOException {

		//Connection with the server
		ServerSocket connectionSocket = new ServerSocket(PORT);
		MasterThread mthread[] = new MasterThread[numWorkers];
		
		for (int i=0; i<numWorkers; i++) {	

			Socket dataSocket = connectionSocket.accept();
			mthread[i] = new MasterThread(dataSocket, i, n);
			mthread[i].start();
		}
		System.out.println("All Started");
		
		for (int i=0; i<numWorkers; i++) {
			try {
				mthread[i].join();
			} catch (InterruptedException e) {}
		}
		System.out.println("All executed!");
		 
		n.printResult(); 
		 	
	}
}



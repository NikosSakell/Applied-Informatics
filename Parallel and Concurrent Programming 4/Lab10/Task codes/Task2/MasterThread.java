import java.io.*;
import java.net.*;

class MasterThread extends Thread
{
	private Socket dataSocket;
	private int myId;
	private InputStream is;
   	private BufferedReader in;
	private OutputStream os;
   	private PrintWriter out;
	private Sum mySum;
	

   	public MasterThread(Socket socket, int id, Sum sum)
   	{
		dataSocket = socket;
		myId = id;
		try {
			is = dataSocket.getInputStream();
			in = new BufferedReader(new InputStreamReader(is));
			os = dataSocket.getOutputStream();
			out = new PrintWriter(os,true);
			mySum = sum;
		}
		catch (IOException e)	{		
	 		System.out.println("I/O Error " + e);
		}
	}

	public void run()
	{
   		String inmsg, outmsg;
		
		try {

			System.out.println("Thread: "+Thread.currentThread().getId()+" started!");
			MasterProtocol app = new MasterProtocol(mySum, myId);
			outmsg = app.prepareRequest();
			out.println(outmsg);
			inmsg = in.readLine();
			app.processReply(inmsg);	
			dataSocket.close();	

		} catch (IOException e)	{		
	 		System.out.println("I/O Error " + e);
		}
	}	
}	
			
		

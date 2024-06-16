import java.net.*;
import java.io.*;

public class MasterProtocol {

	private Sum mySum;
	private int myId;

	public MasterProtocol (Sum sum, int id) {
		mySum = sum;
		myId = id;
	}

	public String prepareRequest() {
		//Setting the request ready to sent to the workers
		int sumNum = Integer.parseInt(mySum.printInit());
		String theOutput = mySum.printInit() + " " + String.valueOf(myId) +" "+ String.valueOf(sumNum);
		return theOutput;
	}
	
	public void processReply(String theInput) {

        double repl = Double.parseDouble(theInput); //Parse Double Instead of Int
        mySum.addTo(repl);
    } 

}

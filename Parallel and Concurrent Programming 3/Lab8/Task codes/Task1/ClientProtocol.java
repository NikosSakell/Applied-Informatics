import java.io.*;

public class ClientProtocol {
    BufferedReader user = new BufferedReader(new InputStreamReader(System.in));

    public String prepareRequest() throws IOException {
        // Get the message from the user
        System.out.print("Enter message to send to server: ");
        String theOutput = user.readLine();
        
        // Get the case selection from the user
        System.out.print("Enter 1 for UPPERCASE, 2 for lowercase, 3 for message Ciphering, 4 for Deciphering: ");
        int caseChoice = Integer.parseInt(user.readLine());

		//1.3 and 1.4
		if (caseChoice == 3 || caseChoice == 4) {  // Get offset for cases 3 and 4
            System.out.print("Enter offset: ");
            int offset = Integer.parseInt(user.readLine());
            return theOutput + "," + caseChoice + "," + offset; 
        } else {
            return theOutput + "," + caseChoice;
        }

    }

    public void processReply(String theInput) throws IOException {
        System.out.println("Message received from server: " + theInput);
    }
}

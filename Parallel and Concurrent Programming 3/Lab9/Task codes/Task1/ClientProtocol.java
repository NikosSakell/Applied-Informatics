import java.net.*;
import java.io.*;

public class ClientProtocol {
    BufferedReader user = new BufferedReader(new InputStreamReader(System.in));

    private String buildRequestMessage(String data) {
        return data; 
    }

    private String getReplyData(String reply) {
        return reply; 
    }

    public String prepareRequest() throws IOException {
        boolean done = false;
        String data = "";
        while (!done) {
            System.out.print("Enter operation (+, -, *, /) and two numbers (e.g., + 5 10): ");
            data = user.readLine();
            done = !data.isEmpty() && data.matches("[+\\-*/]\\s\\d+\\s\\d+") || data.equals("CLOSE"); // Basic input validation
            if (!done) {
                System.out.println("Invalid input. Please try again.");
            }
        }
        return buildRequestMessage(data);
    }

    public void processReply(String reply) {
        if (reply.startsWith("R")) {
            System.out.println("Result: " + getReplyData(reply).substring(2)); // Remove "R " prefix
        } else if (reply.startsWith("E")) {
            System.out.println("Error: " + getReplyData(reply).substring(2)); // Remove "E " prefix
        } else {
            System.out.println("Unexpected reply format: " + reply);
        }
    }

    public String prepareExit() throws IOException {
	
		String theOutput = "EXIT";
		return theOutput;
	}
}

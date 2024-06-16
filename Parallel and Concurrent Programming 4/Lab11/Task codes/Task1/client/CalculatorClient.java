import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.rmi.*;
import java.rmi.registry.*;

public class CalculatorClient {
	
	private static final String HOST = "localhost";
	private static final int PORT = Registry.REGISTRY_PORT; // 1099
	
	public static void main(String[] args) {
				
		try {
			// Locate rmi registry
			
			Registry registry = LocateRegistry.getRegistry(HOST, PORT);
			
			// Look up for a soecific name and get remote reference (stub)
			String rmiObjectName = "MyCalculation";
			Calculator ref = (Calculator)registry.lookup(rmiObjectName);
			
			BufferedReader user = new BufferedReader(new InputStreamReader(System.in));

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

			// Do remote method invocation
			String result = ref.doServerComputation(data);
			System.out.println("The result is: " + result);
		} catch (RemoteException re) {
			System.out.println("Remote Exception");
			re.printStackTrace();
		} catch (Exception e) {
			System.out.println("Other Exception");
			e.printStackTrace();
		}
	}
}


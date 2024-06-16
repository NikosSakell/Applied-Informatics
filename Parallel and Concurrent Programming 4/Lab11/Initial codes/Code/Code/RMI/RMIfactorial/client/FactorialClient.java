import java.rmi.*;
import java.rmi.registry.*;
import java.util.Scanner;
/*
 * Execute:
 * java FactorialClient
 */
public class FactorialClient {
	
	private static final String HOST = "localhost";
	private static final int PORT = Registry.REGISTRY_PORT; // 1099
	
	public static void main(String[] args) {
				
		try {
			// Locate rmi registry
			
			Registry registry = LocateRegistry.getRegistry(HOST, PORT);
			
			// Look up for a soecific name and get remote reference (stub)
			String rmiObjectName = "Factorial";
			Factorial ref = (Factorial)registry.lookup(rmiObjectName);
			
			// Do remote method invocation
			System.out.println("Please insert a number to calculate the factorial");
			Scanner myObj = new Scanner(System.in);
			int num = myObj.nextInt();
			int result = ref.fact(num);
			System.out.println("The factorial of " + num + " is " + result);
		} catch (RemoteException re) {
			System.out.println("Remote Exception");
			re.printStackTrace();
		} catch (Exception e) {
			System.out.println("Other Exception");
			e.printStackTrace();
		}
	}
}


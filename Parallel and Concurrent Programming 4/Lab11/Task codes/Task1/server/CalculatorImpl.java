import java.rmi.*;
import java.rmi.server.*;

public class CalculatorImpl extends UnicastRemoteObject implements Calculator {
	
	private static final long serialVersionUID = 1;

	public CalculatorImpl() throws RemoteException {
	}
	
	public String doServerComputation(String data) {
        String[] parts = data.split("\\s+");  // Split by whitespace
        if (parts.length != 3) {
            return "E Invalid input format";
        }

        String op = parts[0];
        int a = Integer.parseInt(parts[1]);
        int b = Integer.parseInt(parts[2]);

        switch (op) {
            case "+": 
                return "R " + (a + b);
            case "-":
                return "R " + (a - b);
            case "*":
                return "R " + (a * b);
            case "/":
                if (b == 0) {
                    return "E Division by zero";
                }
                return "R " + (a / b);
			case "CLOSE":
				return "CLOSE";
            default: 
                return "E Invalid operator";
        }
    }
}

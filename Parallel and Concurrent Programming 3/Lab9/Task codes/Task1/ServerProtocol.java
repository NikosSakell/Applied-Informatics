import java.net.*;
import java.io.*;

public class ServerProtocol {

    private String buildReplyMessage(String result) {
        return result; 
    }

    private String doServerComputation(String data) {
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

    public String processRequest(String request) {
        if (request.equals("!")) {
            return "!"; // Exit signal
        }
        return buildReplyMessage(doServerComputation(request));
    }
}

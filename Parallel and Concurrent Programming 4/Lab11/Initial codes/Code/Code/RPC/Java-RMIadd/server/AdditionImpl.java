import java.rmi.*;
import java.rmi.server.*;

// H klash ayth ylopoiei thn apomakrysmenh diepafh Factorial.
public class AdditionImpl extends UnicastRemoteObject implements Addition {
	
	private static final long serialVersionUID = 1;

	// Kataskeyasths.
	public AdditionImpl() throws RemoteException {
	}
	
	// Kodikas ylopoihshs ths apomakrysmenhs methodoy.
	public int add(int a, int b) throws RemoteException {
		return a+b;
	}
}

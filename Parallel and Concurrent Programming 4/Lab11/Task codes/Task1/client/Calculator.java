import java.rmi.*;

public interface Calculator extends Remote {
	
	// Ypografh ths apomakrysmenhs methodoy.
	public String doServerComputation(String data) throws RemoteException;
}

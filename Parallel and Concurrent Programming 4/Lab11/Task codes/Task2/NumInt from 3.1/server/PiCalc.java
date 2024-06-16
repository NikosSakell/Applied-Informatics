import java.rmi.*;

public interface PiCalc extends Remote {
	
	// Ypografh ths apomakrysmenhs methodoy.
	public double calcPi(long start, long stop, double step) throws RemoteException;
}

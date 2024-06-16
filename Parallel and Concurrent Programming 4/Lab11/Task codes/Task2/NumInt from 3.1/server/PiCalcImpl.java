import java.rmi.*;
import java.rmi.server.*;

public class PiCalcImpl extends UnicastRemoteObject implements PiCalc {
	
	private static final long serialVersionUID = 1;

	public PiCalcImpl() throws RemoteException {
	}
	
	public double calcPi(long start, long stop, double step) throws RemoteException {
		double local_sum = 0;
		for(long i= start ; i<stop; i++){
            double x = ((double)i+0.5)*step;
            local_sum += 4.0/(1.0+x*x);
        }
        // aSum.add(local_sum);
		return local_sum;
	}
}

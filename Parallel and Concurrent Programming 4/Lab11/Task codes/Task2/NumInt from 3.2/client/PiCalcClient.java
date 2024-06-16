import java.rmi.*;
import java.rmi.registry.*;
import java.util.HashMap;
import java.util.Scanner;

public class PiCalcClient {
	
	private static final String HOST = "localhost";
	private static final int PORT = Registry.REGISTRY_PORT; // 1099
	private static final int numOfCores = Runtime.getRuntime().availableProcessors();
	public static HashMap<Integer, Double> savedPi = new HashMap<Integer, Double>();
	
	public static void main(String[] args) {
				
		try {
			// Locate rmi registry
			
			Registry registry = LocateRegistry.getRegistry(HOST, PORT);
			
			// Look up for a soecific name and get remote reference (stub)
			String rmiObjectName = "MyPiCalculation";
			PiCalc ref = (PiCalc)registry.lookup(rmiObjectName);

			Scanner scanner = new Scanner(System.in);
			double sum = 0;
			double pi = 0;
			int n = 0;

			boolean close=false;

			while(!close){

				System.out.println("Please insert a n value to calculate Pi, or 0 to terminate operation");
				n = scanner.nextInt();
				if(n == 0){
					close=true;
					break;
				}

				double step = 1.0 / (double)n;

				int start=0;
				int stop=0;
				int block=0;
			
				
				if(!savedPi.containsKey(n)){
					System.out.println("New calculation!");
					for(int i=0; i<numOfCores; i++){
						start = i * block;
						stop = i*block + block;
						if(i==(numOfCores-1)) stop = n;

						// Do remote method invocation
						sum = sum + ref.calcPi(start, stop, step);
					}

					pi = sum * step;	
					savedPi.put(n, pi);
				}else{
					pi = savedPi.get(n);
				}
					
				System.out.println("The Pi calculation for n = "+ n + " is "+ pi);
			
				System.out.printf("difference between estimated pi and Math.PI = %22.20f\n", Math.abs(pi - Math.PI));
			}	
			

		} catch (RemoteException re) {
			System.out.println("Remote Exception");
			re.printStackTrace();
		} catch (Exception e) {
			System.out.println("Other Exception");
			e.printStackTrace();
		}
	}

}


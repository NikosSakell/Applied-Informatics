import java.net.*;
import java.io.*;

public class WorkerProtocol {

	private int numWorkers;
	
	//Number of workers
	public WorkerProtocol(int num) {
		numWorkers = num;
	}	

	public String compute(String theInput) throws IOException {
	
		String[] splited = theInput.split("\\s+");
		int range = Integer.parseInt(splited[0]);
		int id = Integer.parseInt(splited[1]);
		int numSteps = Integer.parseInt(splited[2]);
		double step = 1.0 / (double)numSteps;
		
		System.out.println("Worker "+ id +" calculates " + range);
		int block = range / numWorkers;
		int start = id * block;
		int stop = start + block;
		if (id == numWorkers-1) stop = range;
		System.out.println("Worker "+ id +" calculates from " + start + "to " +stop);
		
		double local_sum = 0.0; // Use double for the sum
        for (int i = start; i < stop; i++) {
            double x = ((double) i + 0.5) * step;
            local_sum += 4.0 / (1.0 + x * x);
        }

        String theOutput = String.valueOf(local_sum * step); // Multiply by step
        System.out.println("Worker " + id + " result " + theOutput);

        return theOutput;
	}
}

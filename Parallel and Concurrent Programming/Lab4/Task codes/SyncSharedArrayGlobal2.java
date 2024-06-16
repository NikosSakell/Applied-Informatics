// Synchronized SharedCounterArrayGlobal κώδικας χωρίς τη χρήση Object:

public class SyncSharedArrayGlobal2 {

    public static void main(String[] args) {

		int end = 1000;
    	SynchronizedArray array = new SynchronizedArray(end);
    	int numThreads = 4;

		TheCounterThread threads[] = new TheCounterThread[numThreads];
		
		for (int i = 0; i < numThreads; i++) {
			threads[i] = new TheCounterThread(array, end);
			threads[i].start();
		}
	
		for (int i = 0; i < numThreads; i++) {
			try {
				threads[i].join();
			}
			catch (InterruptedException e) {}
		} 
		check_array (array, end, numThreads);
    }
     
    static void check_array (SynchronizedArray array, int end, int numThreads)  {
		int i, errors = 0;

		System.out.println ("Checking...");

        for (i = 0; i < end; i++) {
			if (array.get(i) != numThreads*i) {
				errors++;
				System.out.printf("%d: %d should be %d\n", i, array.get(i), numThreads*i);
			}         
        }
        System.out.println (errors+" errors.");
    }


    
}


class TheCounterThread extends Thread {
  	
	SynchronizedArray array;
	int end;

	public TheCounterThread(SynchronizedArray array, int end) {
		this.end = end;
		this.array = array;
	}
   
	public void run() {

		for (int i = 0; i < end; i++) {
			for (int j = 0; j < i; j++)
				array.inc(i);		
		}
		  
	}            	
}
 

class SynchronizedArray{

	int[] array;
	int end;

	public SynchronizedArray(int end){
		this.end = end;
		array = new int[end];
	}

	public synchronized int get(int i){
		return array[i];
	}
	
	public synchronized void inc(int i){
		array[i]++;
	}

}

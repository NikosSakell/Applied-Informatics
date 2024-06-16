
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// Mutex SharedCounterArrayGlobal κώδικας με χρήση lock:

public class MutexSharedArrayG2 {

    static int end = 1000;
    static int[] array = new int[end];
    static int numThreads = 4;
    static Lock lock = new ReentrantLock();

    public static void main(String[] args) {

		CounterThread threads[] = new CounterThread[numThreads];
		
		for (int i = 0; i < numThreads; i++) {
			threads[i] = new CounterThread();
			threads[i].start();
		}
	
		for (int i = 0; i < numThreads; i++) {
			try {
				threads[i].join();
			}
			catch (InterruptedException e) {}
		} 
		check_array ();
    }
     
    static void check_array ()  {
		int i, errors = 0;

		System.out.println ("Checking...");

        for (i = 0; i < end; i++) {
			if (array[i] != numThreads*i) {
				errors++;
				System.out.printf("%d: %d should be %d\n", i, array[i], numThreads*i);
			}         
        }
        System.out.println (errors+" errors.");
    }


    static class CounterThread extends Thread {
  	
       public CounterThread() {
       }
  	
       public void run() {

        //Διόρθωση με το lock μέσα στη for για βελτιστοποίηση της απόδοσης
  
            for (int i = 0; i < end; i++) {
				lock.lock();
				try {
					for (int j = 0; j < i; j++)
						array[i]++;		
				} finally {
				    lock.unlock();
                }
			}	
            
		}            	
    }
}

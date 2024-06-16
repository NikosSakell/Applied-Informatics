import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class GlobalVarsLock {

    static int n = 0;
	//Declaring lock as a global variable
    static Lock lock = new ReentrantLock();
    
    public static void main(String[] args) {

        int numThreads = 4;  
		CounterThread counterThreads[] = new CounterThread[numThreads];
		for (int i = 0; i < numThreads; i++) {
		    counterThreads[i] = new CounterThread();
		    counterThreads[i].start();
		}

		//Στον αρχικό κώδικα, η μεταβλητή i χρησιμοποιείται για να αναθέσει ένα 
		//μοναδικό αναγνωριστικό σε κάθε νήμα (thread), το οποίο στη συνέχεια 
		//χρησιμοποιείται για να εμφανιστεί στην έξοδο. Αυτή η λειτουργικότητα 
		//δεν είναι πλέον απαραίτητη όταν χρησιμοποιείται ένα lock για την 
		//αμοιβαία αποκλειστική πρόσβαση. Ως εκ τούτου, δεν χρειάζεται πλέον 
		//η μεταβλητή i ή το tid, καθώς δεν χρησιμοποιούνται πλέον για κάποιο 
		//συγκεκριμένο σκοπό στον τροποποιημένο κώδικα. 

        for (int i = 0; i < numThreads; i++) {
            try {
		        counterThreads[i].join();
            }
            catch (InterruptedException e) {}
		} 
	    System.out.println("n = "+n); 
    }

    static class CounterThread extends Thread {
	  	//No need to declare lock inside the class as it is global
	public void run() {

	    for (int i = 0; i < 10000; i++) {
	        lock.lock();
			try { 
			    n = n + 1;
		    } finally {
				lock.unlock() ;
			}
	        }	
		}
    }
}	
	

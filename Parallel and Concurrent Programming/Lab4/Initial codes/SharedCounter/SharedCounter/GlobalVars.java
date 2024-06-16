   
public class GlobalVars {

    static int n = 0;

    public static void main(String[] args) {

        int numThreads = 10;  

		CounterThread counterThreads[] = new CounterThread[numThreads];
		for (int i = 0; i < numThreads; i++) {
		    counterThreads[i] = new CounterThread(i);
		    counterThreads[i].start();
		}

        for (int i = 0; i < numThreads; i++) {
            try {
	        counterThreads[i].join();
            }
            catch (InterruptedException e) {}
		} 
        System.out.println("Main: n = "+n); 
    }

    static class CounterThread extends Thread {
	  	
		int threadID;
		    
		public CounterThread(int tid) {
		    this.threadID = tid;
		}
		  	
		public void run() {
		  
		    for (int i = 0; i < 10000; i++) {
		            n = n + 1;
		    }
		    System.out.println("Thread "+threadID+ " n = "+n); 
		}
    }
}	
	

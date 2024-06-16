
public class MainArgs {

    public static void main(String[] args) {

        SharedCounter counter = new SharedCounter();
        
        int numThreads = 10;  	
		CounterThread counterThreads[] = new CounterThread[numThreads];
		for (int i = 0; i < numThreads; i++) {
		    counterThreads[i] = new CounterThread(i, counter);
		    counterThreads[i].start();
		}
	
	    for (int i = 0; i < numThreads; i++) {
	        try {
				counterThreads[i].join();
	        }
	        catch (InterruptedException e) {}
		}
		 
        System.out.println("n = "+counter.get());
    }

}

class SharedCounter {

    int n;
    
    public SharedCounter (){
		this.n = 0;
    }
    
    public int get() {
        return n;
    }    
    
    public void inc() {
        n = n + 1;
    } 
    
       
}

class CounterThread extends Thread {
  	
    int threadID;
    SharedCounter count;
    
    public CounterThread(int tid, SharedCounter counter) {
        this.threadID = tid;
        this.count = counter;
    }
  	
    public void run() {

        for (int i = 0; i < 10000; i++) {
           	    count.inc();
		}
		
		System.out.println("Thread "+threadID+ " n = "+count.get());
    }
}

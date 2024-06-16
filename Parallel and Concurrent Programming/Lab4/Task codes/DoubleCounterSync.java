// Task 4
// Υλοποίηση της κλάσης Double Counter με χρήση της μεθοδολογίας για κλειδώματα synchronized back και front:

public class DoubleCounterSync {
    
    static int numThreads = Runtime.getRuntime().availableProcessors();

    public static void main(String[] args) {
        
        CounterThread threads[] = new CounterThread[numThreads];
		DoubleCounter counter = new DoubleCounter();

		for (int i = 0; i < numThreads; i++) {
			threads[i] = new CounterThread(counter);
			threads[i].start();
		}
	
		for (int i = 0; i < numThreads; i++) {
			try {
				threads[i].join();
			}
			catch (InterruptedException e) {}
		} 

        System.out.println("n1 = "+counter.getN1()+" n2 = "+counter.getN2());
    }

}

class DoubleCounter{

    int n1, n2;
    Object front_l = new Object();
    Object back_l = new Object();

    public DoubleCounter(){
        this.n1 = 0;
        this.n2 = 0;    
    }

    void incN1(){
        synchronized(back_l){
            n1++;
        }
    }

    void incN2(){
        synchronized(back_l){
            n2++;
        }
    }

    public int getN1(){
        synchronized(front_l){
            return n1;
        }
    }

    public int getN2(){
        synchronized(front_l){
            return n2;
        }
    }

}

class CounterThread extends Thread{

    DoubleCounter counter;

    public CounterThread(DoubleCounter aCounter){
        counter = aCounter;
    }

    public void run(){
        for(int i=0; i<1000; i++){
            counter.incN1();
            counter.incN2();
        }
    }

}


// Τα αποτελέσματα που έπαιρνα πριν την υλοποίηση των object synchronizations δεν ήταν πάντα σωστά
// Ενδεικτικά: n1 = 5908 n2 = 4949

// Μετά την υλοποίηση τα αποτελέσματα είχαν τη μορφή:
// n1 = 8000 n2 = 8000
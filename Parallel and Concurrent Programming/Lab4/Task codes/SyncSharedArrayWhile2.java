// Synchronized SharedCounterArrayGlobalWhile κώδικας χωρίσ τη χρήση Object με αντικειμενοστρέφεια:

public class SyncSharedArrayWhile2 {
    
    public static void main(String[] args) {

        int end = 10000;
        int counter = 0;
        ASynchronizedArray array = new ASynchronizedArray(end, counter);
        int numThreads = 4;

        ACounterThread threads[] = new ACounterThread[numThreads];
	
		for (int i = 0; i < numThreads; i++) {
			threads[i] = new ACounterThread(counter, array, end);
			threads[i].start();
		}
	
		for (int i = 0; i < numThreads; i++) {
			try {
				threads[i].join();
			}
			catch (InterruptedException e) {}
		} 
        check_array (array, end);
    }
     
    static void check_array (ASynchronizedArray array, int end)  {
		int i, errors = 0;

		System.out.println ("Checking...");

        for (i = 0; i < end; i++) {
			if (array.get(i) != 1) {
				errors++;
				System.out.printf("%d: %d should be 1\n", i, array.get(i));
			}         
		}
        System.out.println (errors+" errors.");
    }


    
}



class ACounterThread extends Thread {

    int counter;
    ASynchronizedArray array;
    int end;
  	
    public ACounterThread(int counter, ASynchronizedArray anArray, int anEnd) {
        this.counter = counter;
        this.array = anArray;
        this.end = anEnd;
    }
   
    public void run() {
    
        while (true) {
            if (counter >= end) 
                	break;
            array.inc();		
        } 
     }            	
 }


 class ASynchronizedArray{

    int[] array;
    int end;
    int counter;

    public ASynchronizedArray(int end, int counter){
        this.end = end;
        this.counter = counter;
        array = new int[end];
    }

    public synchronized int get(int i){
        return array[i];
    }

    public synchronized void inc(){
            array[counter]++;
            counter++;   
    }

 }
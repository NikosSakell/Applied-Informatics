public class SharedCounterArrayGlobalWhileAlt {
  
    public static void main(String[] args) {

		int end = 10000;
    	int counter = 0;
    	int[] array = new int[end];
    	int numThreads = 4;

        TheCounterThread threads[] = new TheCounterThread[numThreads];
	
		for (int i = 0; i < numThreads; i++) {
			threads[i] = new TheCounterThread(end, counter, array);
			threads[i].start();
		}
	
		for (int i = 0; i < numThreads; i++) {
			try {
				threads[i].join();
			}
			catch (InterruptedException e) {}
		} 
        check_array (end, array);
    }
     
    static void check_array (int end, int[] array)  {
		
		int i, errors = 0;

		System.out.println ("Checking...");

        for (i = 0; i < end; i++) {
			if (array[i] != 1) {
				errors++;
				System.out.printf("%d: %d should be 1\n", i, array[i]);
			}         
		}
        System.out.println (errors+" errors.");
    }

}
  

class TheCounterThread extends Thread {
  	
	int counter;
	int end;
	int[] array;

	public TheCounterThread(int anEnd, int aCounter, int[] anArray) {
		counter = aCounter;
		end = anEnd;
		array = anArray;
	}
   
	public void run() {
	
		 while (true) {
			 if (counter >= end) 
				 break;
			 array[counter]++;
			 counter++;		
		 } 
	 }            	
 }
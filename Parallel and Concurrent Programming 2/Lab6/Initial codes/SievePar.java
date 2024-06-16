// import java.io.IOException;
// import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class SievePar {
    public static void main(String[] args) {
        
		int size = 0;
		if (args.length != 1) {
			System.out.println("Usage: java SieveOfEratosthenes <size>");
			System.exit(1);
		}

		try {
			size = Integer.parseInt(args[0]);
		}
		catch (NumberFormatException nfe) {
	   		System.out.println("Integer argument expected");
    		System.exit(1);
		}
		if (size <= 0) {
				System.out.println("size should be positive integer");
	    		System.exit(1);
		}

		boolean[] prime = new boolean[size+1];

		for(int i = 2; i <= size; i++)
			prime[i] = true; 		


        int numThreads = Runtime.getRuntime().availableProcessors();
        int block = size / numThreads;
        SieveThread[] myThreads = new SieveThread[numThreads];

        long start_time = System.currentTimeMillis();

        for (int i = 0; i < numThreads; i++) {
            int threadStart = i * block;
            int threadStop = (i == numThreads - 1) ? size : threadStart + block - 1;

            myThreads[i] = new SieveThread(threadStart, threadStop, prime);
            myThreads[i].start();
        }


        for (int i = 0; i < numThreads; i++) {
            try { 
                myThreads[i].join();
            } catch (InterruptedException e) {}
        }

        
        int count = 0;
        for (SieveThread thread : myThreads) {
            count += thread.getLocalCount();
        }

        long elapsedTimeMillis = System.currentTimeMillis()-start_time;
				
		System.out.println("number of primes "+count); 
		System.out.println("time in ms = "+ elapsedTimeMillis);
    }
}

class SieveThread extends Thread {
    private int start;
    private int stop;
    private boolean[] prime;
    private int localCount = 0; 
    private static ReentrantLock countLock = new ReentrantLock();

    public SieveThread(int start, int stop, boolean[] prime) {
        this.start = start;
        this.stop = stop;
        this.prime = prime;
    }

    @Override
    public void run() {
        int limit = (int) Math.sqrt(stop) + 1;
        for (int p = 2; p <= limit; p++) {
            if (prime[p]) {
                for (int i = Math.max(p * p, (start + p - 1) / p * p); i <= stop; i += p) {
                    prime[i] = false;
                }
            }
        }

        countLock.lock(); 
        try {
            localCount += countPrimes();
        } finally {
            countLock.unlock();
        }
    }

    private int countPrimes() {
        int count = 0;
        for (int i = start; i <= stop; i++) {
            if (prime[i]) {
                count++;
            }
        }
        return count;
    }

    public int getLocalCount() {
        return localCount;
    }
}


//Χρόνοι Εκτέλεσης για είσοδο 2000000000:
//number of primes 98222287
//time in ms = 25880
//Περίπου 3000 ms πιο γρήγορο από το ακολουθιακό
import java.util.concurrent.locks.ReentrantLock;

class SieveParCyclic {
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


        boolean[] prime = new boolean[size + 1];
        for (int i = 2; i <= size; i++)
            prime[i] = true;

        int numThreads = Runtime.getRuntime().availableProcessors();
        SieveThreadCyclic[] myThreads = new SieveThreadCyclic[numThreads];

        long start_time = System.currentTimeMillis();

        for (int i = 0; i < numThreads; i++) {
            myThreads[i] = new SieveThreadCyclic(i, numThreads, size, prime);
            myThreads[i].start();
        }

        for (int i = 0; i < numThreads; i++) {
            try { 
                myThreads[i].join();
            } catch (InterruptedException e) {}
        }

        int count = 0;
        for (SieveThreadCyclic thread : myThreads) {
            count += thread.getLocalCount();
        }

        long elapsedTimeMillis = System.currentTimeMillis() - start_time;

        System.out.println("number of primes " + count); 
        System.out.println("time in ms = " + elapsedTimeMillis);
    }
}

class SieveThreadCyclic extends Thread {
    private int threadId;
    private int numThreads;
    private int size;
    private boolean[] prime;
    private int localCount = 0; 
    private static ReentrantLock countLock = new ReentrantLock(); 

    public SieveThreadCyclic(int threadId, int numThreads, int size, boolean[] prime) {
        this.threadId = threadId;
        this.numThreads = numThreads;
        this.size = size;
        this.prime = prime;
    }

    @Override
    public void run() {
        int limit = (int) Math.sqrt(size) + 1;

        for (int p = 2; p <= limit; p++) {
            if (prime[p]) {

                for (int i = p * p; i <= size; i += p) {
                    if (i % numThreads == threadId) {
                        prime[i] = false;
                    }
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
        for (int i = threadId; i <= size; i += numThreads) {
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
//time in ms = 96848
import java.util.concurrent.locks.ReentrantLock;

class DynamicSieve {
    private final int size;
    private final int numThreads;
    private final boolean[] prime;
    private final ReentrantLock lock;

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


        DynamicSieve sieve = new DynamicSieve(size);
        sieve.calculatePrimes();

        System.out.println("number of primes " + sieve.countPrimes());
    }

    public DynamicSieve(int size) {
        this.size = size;
        this.numThreads = Runtime.getRuntime().availableProcessors();
        this.prime = new boolean[size+1];
        this.lock = new ReentrantLock();

        for (int i = 2; i <= size; i++) {
            prime[i] = true;
        }
    }

    public void calculatePrimes() {
        int totalTasks = size - 1;
        SieveThread[] myThreads = new SieveThread[numThreads];

        long startTime = System.currentTimeMillis();

        for (int i = 0; i < numThreads; i++) {
            myThreads[i] = new SieveThread(totalTasks, prime, lock);
            myThreads[i].start();
        }

        for (int i = 0; i < numThreads; i++) {
            try {
                myThreads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        long elapsedTimeMillis = System.currentTimeMillis() - startTime;
        System.out.println("time in ms = " + elapsedTimeMillis);
    }

    public int countPrimes() {
        int count = 0;
        for (int i = 2; i <= size; i++) {
            if (prime[i]) {
                count++;
            }
        }
        return count;
    }

    private static int getTask(int totalTasks, ReentrantLock lock) {
        lock.lock();
        try {
            if (++tasksAssigned < totalTasks) {
                return tasksAssigned + 2;
            } else {
                return 0;
            }
        } finally {
            lock.unlock();
        }
    }

    class SieveThread extends Thread {
        private final int totalTasks;
        private final boolean[] prime;
        private final ReentrantLock lock;

        public SieveThread(int totalTasks, boolean[] prime, ReentrantLock lock) {
            this.totalTasks = totalTasks;
            this.prime = prime;
            this.lock = lock;
        }

        @Override
        public void run() {
            int p;
            while ((p = DynamicSieve.getTask(totalTasks, lock)) != 0) {

                if (prime[p]) { 
                    for (int i = p * p; i <= (size); i += p) {
                        prime[i] = false;
                    }
                }
            }
        }
    }

    private static int tasksAssigned = -1; 
}

//Χρόνοι Εκτέλεσης για είσοδο 2000000000:
//number of primes 98222287
//time in ms = 25350

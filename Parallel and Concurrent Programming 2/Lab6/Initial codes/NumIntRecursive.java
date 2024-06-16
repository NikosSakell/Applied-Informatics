import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class NumIntRecursive {
    public static void main(String[] args) {


        long numSteps = 10000;
        double step = 1.0 / (double) numSteps;
        int limit = 128; 
        Lock lock = new ReentrantLock();

        long startTime = System.currentTimeMillis();

        RecThread rootTask = new RecThread(0, numSteps, limit, step, lock);
        rootTask.start();


        try {
            rootTask.join();
            double pi = rootTask.result * step; 

            long endTime = System.currentTimeMillis();
            System.out.printf("sequential program results with %d steps\n", numSteps);
            System.out.printf("computed pi = %22.20f\n" , pi);
            System.out.printf("difference between estimated pi and Math.PI = %22.20f\n", Math.abs(pi - Math.PI));
            System.out.printf("time to compute = %f seconds\n", (double) (endTime - startTime) / 1000);
        } catch (InterruptedException e) {}
    }
}

class RecThread extends Thread { 

    private long start;
    private long stop;
    private int limit;
    private double step;
    public int result;
    Lock lock;

    public RecThread(long index, long numSteps, int limit, double step, Lock lock){
        this.stop = numSteps;
        this.start = index;
        this.limit = limit;
        this.step = step;
        this.lock = lock;
    }

    @Override
    public void run() {
        long workload = stop - start;
        if (workload <= limit) {
            for (long i=start; i < stop; ++i) {
                double x = ((double)i+0.5)*step;
                result += 4.0/(1.0+x*x);
            }
        } else {
            long mid = start + workload / 2;
            RecThread leftTask = new RecThread(start, mid, limit, step, lock);
            RecThread rightTask = new RecThread(mid, stop, limit, step, lock);
            leftTask.start(); 
            rightTask.start();

            try {
                leftTask.join();
                rightTask.join();
                lock.lock();
                try{
                    result = leftTask.result + rightTask.result;
                }finally{
                    lock.unlock();
                }
            } catch (InterruptedException e) {}
        }
    }
}

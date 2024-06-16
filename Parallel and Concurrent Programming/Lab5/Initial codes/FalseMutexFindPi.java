//Ερώτημα 4

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class FalseMutexFindPi {
    // static long numSteps;
    // static double sum;

    public static void main(String[] args) {

        int numThreads = Runtime.getRuntime().availableProcessors();
        long numSteps = 10000;
        double sum = 0.0;
        double[] sums = new double[numThreads]; 

        Lock lock = new ReentrantLock();

        CalcPiThread threads[] = new CalcPiThread[numThreads];

        long block = numSteps/numThreads;
        long start = 0;
        long stop = 0;


        for(int i=0; i<numThreads; i++){
            sums[i] = 0;
        }

        /* start timing */
        long startTime = System.currentTimeMillis();

        double step = 1.0 / (double)numSteps;
        /* do computation */

        for(int i=0; i<numThreads; i++){

            start = i * block;
            stop = i*block + block;
            if(i==(numThreads-1)) stop = numSteps;

            threads[i] = new CalcPiThread(sums, i, start, stop, step, lock);
            threads[i].start();
        }


        for(int i=0; i<numThreads; i++){
            try{
                threads[i].join();
            }catch(InterruptedException e){}
            sum = sum + sums[i];
        }

        double pi = sum * step;

        /* end timing and print result */
        long endTime = System.currentTimeMillis();
        System.out.printf("parallel program results with %d steps\n", numSteps);
        System.out.printf("computed pi = %22.20f\n" , pi);
        System.out.printf("difference between estimated pi and Math.PI = %22.20f\n", Math.abs(pi - Math.PI));
        System.out.printf("time to compute = %f seconds\n", (double) (endTime - startTime) / 1000);
    }

}



class CalcPiThread extends Thread{
        
    int index;
    long start;
    long stop;
    double step;
    double local_sum = 0;
    Lock lock;
    double[] sums;

    public CalcPiThread(double[] sums, int index, long start, long stop, double step, Lock lock){
        this.index = index;
        this.sums=sums;
        this.lock = lock;
        this.start = start;
        this.stop = stop;
        this.step = step;
    }

    public void run(){
        for(long i= start ; i<stop; i++){

            lock.lock();
            try{
                double x = ((double)i+0.5)*step;
                local_sum += 4.0/(1.0+x*x);
            }finally{
                lock.unlock();
            }
        }
        sums[index] = sums[index] + local_sum;
    }
    }

//Results with false Mutex:

//10000 steps
//computed pi = 3,14159265442312600000
//difference between estimated pi and Math.PI = 0,00000000083333295819        
//time to compute = 0,006000 seconds

//Συγκριτικά με το FindPiMutex.java που είχε 0,001000 seconds
//ο χρόνος εκτέλεσης έχει γίνει έξι φορές μεγαλύτερος.


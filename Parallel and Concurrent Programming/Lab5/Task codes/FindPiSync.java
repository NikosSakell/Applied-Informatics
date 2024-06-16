//Ερ΄ώτημα 3

public class FindPiSync {
    // static long numSteps;
    // static double sum;

    public static void main(String[] args) {

        int numThreads = Runtime.getRuntime().availableProcessors();
        long numSteps = 100000;
        sumObject aSum = new sumObject();
        double[] sums = new double[numThreads]; 

        ThePiThread threads[] = new ThePiThread[numThreads];

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

            threads[i] = new ThePiThread(aSum, sums, i, start, stop, step);
            threads[i].start();
        }


        for(int i=0; i<numThreads; i++){
            try{
                threads[i].join();
            }catch(InterruptedException e){}
            aSum.add(sums[i]);
        }

        double pi = aSum.get() * step;

        /* end timing and print result */
        long endTime = System.currentTimeMillis();
        System.out.printf("parallel program results with %d steps\n", numSteps);
        System.out.printf("computed pi = %22.20f\n" , pi);
        System.out.printf("difference between estimated pi and Math.PI = %22.20f\n", Math.abs(pi - Math.PI));
        System.out.printf("time to compute = %f seconds\n", (double) (endTime - startTime) / 1000);
    }

}


class ThePiThread extends Thread{
        
    int index;
    long start;
    long stop;
    double step;
    double[] sums;
    double local_sum = 0;
    sumObject aSum;

    public ThePiThread(sumObject aSum, double[] sums, int index, long start, long stop, double step){
        this.aSum = aSum;
        this.index = index;
        this.sums=sums;
        this.start = start;
        this.stop = stop;
        this.step = step;
    }

    public void run(){
        for(long i= start ; i<stop; i++){
            double x = ((double)i+0.5)*step;
            local_sum += 4.0/(1.0+x*x);
        }
        aSum.add(local_sum);
        
    }
    }


class sumObject{

    double sum;

    public sumObject(){
        this.sum=0;
    }

    public synchronized void add(double localsum){
        this.sum = this.sum + localsum;
    }

    // public synchronized void print(){
    //     System.out.println(this.sum);
    // }

    public synchronized double get(){
        return this.sum;
    }

}


//Results:
//parallel synchronized

//10000 steps
//computed pi = 3,14159265442312600000
//difference between estimated pi and Math.PI = 0,00000000083333295819
//time to compute = 0,001000 seconds

//20000 steps
//computed pi = 3,14159265379813140000
//difference between estimated pi and Math.PI = 0,00000000020833823555        
//time to compute = 0,002000 seconds

//100000 steps
//computed pi = 3,14159265359812560000
//difference between estimated pi and Math.PI = 0,00000000000833244584        
//time to compute = 0,004000 seconds
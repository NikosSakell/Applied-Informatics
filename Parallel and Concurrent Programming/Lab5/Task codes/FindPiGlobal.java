//Ερώτημα 1

public class FindPiGlobal {

    // static long numSteps;
    // static double sum;
    static int numThreads = Runtime.getRuntime().availableProcessors();
    static double[] sums = new double[numThreads]; 

    public static void main(String[] args) {

        long numSteps = 150000;
        double sum = 0.0;

        PiThread threads[] = new PiThread[numThreads];

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

            threads[i] = new PiThread(i, start, stop, step);
            threads[i].start();
        }


        for(int i=0; i<numThreads; i++){
            try{
                threads[i].join();
            }catch(InterruptedException e){}
            sum = sum + sums[i];
        }

        /* parse command line
        if (args.length != 1) {
		System.out.println("arguments:  number_of_steps");
                System.exit(1);
        }
        try {
		numSteps = Long.parseLong(args[0]);
        } catch (NumberFormatException e) {
		System.out.println("argument "+ args[0] +" must be long int");
		System.exit(1);
        }*/
        
        double pi = sum * step;

        /* end timing and print result */
        long endTime = System.currentTimeMillis();
        System.out.printf("parallel program results with %d steps\n", numSteps);
        System.out.printf("computed pi = %22.20f\n" , pi);
        System.out.printf("difference between estimated pi and Math.PI = %22.20f\n", Math.abs(pi - Math.PI));
        System.out.printf("time to compute = %f seconds\n", (double) (endTime - startTime) / 1000);
    }


    static class PiThread extends Thread{
        
        int index;
        long start;
        long stop;
        double step;
        double local_sum = 0;

        public PiThread(int index, long start, long stop, double step){
            this.index = index;
            this.start = start;
            this.stop = stop;
            this.step = step;
        }

        public void run(){
            for(long i= start ; i<stop; i++){
                double x = ((double)i+0.5)*step;
                local_sum += 4.0/(1.0+x*x);
            }
            sums[index] = sums[index] + local_sum;
        }
        }
}



//Results:
//parallel

//10000 steps
//computed pi = 3,14159265442312600000
//difference between estimated pi and Math.PI = 0,00000000083333295819
//time to compute = 0,001000 seconds

//20000 steps
//computed pi = 3,14159265379813140000
//difference between estimated pi and Math.PI = 0,00000000020833823555
//time to compute = 0,001000 seconds

//50000 steps
//computed pi = 3,14159265362312900000
//difference between estimated pi and Math.PI = 0,00000000003333600063
//time to compute = 0,007000 seconds

//150000 steps
//computed pi = 3,14159265359350000000
//difference between estimated pi and Math.PI = 0,00000000000370681263
//time to compute = 0,004000 seconds
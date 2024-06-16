import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*; 
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class StringMatchPar {

    public static void main(String args[]) throws IOException {

        long startTime = System.currentTimeMillis();
    
        if (args.length != 2) {
			System.out.println("BruteForceStringMatch  <file name> <pattern>");
			System.exit(1);
        }

        //Μετατροπή των string του αρχείου (όλες τις λέξεις) και του pattern (πχ. caccattgggaatccagg) σε
        //char arrays για ευκολότερη σύγκριση

        //String αρχείου
        String fileString = new String(Files.readAllBytes(Paths.get(args[0])));//, StandardCharsets.UTF_8);
        char[] text = new char[fileString.length()]; 
        int n = fileString.length();
        for (int i = 0; i < n; i++) { 
            text[i] = fileString.charAt(i); 
        } 
 
        //String του pattern
        String patternString = new String(args[1]);                                                
        char[] pattern = new char[patternString.length()]; 
        int m = patternString.length(); 
        for (int i = 0; i < m; i++) { 
            pattern[i] = patternString.charAt(i); 
        }

        //Δημιουργία του πίνακα match όπου θα αποθηκεύονται τα αποτελέσματα των συγκρίσεων
        //Αρχικοποίηση του πίνακα με 0, όταν βρίσκεται κάποιο match η αντίστοιχη θέση θα γίνεται 1

        int matchLength = n - m;
        char[] match = new char[matchLength+1]; 
        for (int i = 0; i <= matchLength; i++) { 
            match[i] = '0'; 
        }

        int numThreads = Runtime.getRuntime().availableProcessors();
        MatchThread myThreads[] = new MatchThread[numThreads];

        int block;
        int start = 0;
        int stop = 0;
        int matchCount = 0;
        int[] totalMatches = new int[numThreads];

        Lock lock = new ReentrantLock();

        for(int i=0; i<numThreads; i++){
            totalMatches[i] = 0;
        }

        block = matchLength/numThreads;

        for(int i=0; i<numThreads; i++){

            start = i*block;
            stop = i*block + block;

            if(i == (numThreads - 1)) stop = matchLength;

            myThreads[i] =  new MatchThread(lock, m, start, stop, totalMatches, text, pattern, match, i);
            myThreads[i].start();

        }

        for(int i=0; i<numThreads; i++){
            try{
                myThreads[i].join();
            }catch(InterruptedException e){}
            matchCount = matchCount + totalMatches[i];
        }

        /*ArrayList<Integer> match = new ArrayList<Integer>(); */


        long endTime = System.currentTimeMillis();

        for (int i = 0; i < matchLength; i++) { 
            if (match[i] == '1') System.out.print(i+" ");
        }
        System.out.println();
        System.out.printf("Time to compute = %f seconds\n", (double) (endTime - startTime) / 1000);  
        System.out.println("Total matches " + matchCount);
        
   }
}


class MatchThread extends Thread{

    private int m;
    private int start;
    private int stop;
    private char[] text;
    private char[] pattern;
    private char[] match;
    private int[] totalMatches;
    private int index;

    Lock lock;

    public MatchThread(Lock lock, int m, int start, int stop, int[] totalMatches, char[] text, char[] pattern, char[] match, int index){
        this.lock = lock;
        this.m = m;
        this.start = start;
        this.stop = stop;
        this.totalMatches = totalMatches;
        this.text = text;
        this.pattern = pattern;
        this.match = match;
        this.index = index;
    }


    public void run(){

        // System.out.println("Current start: "+start + ", current stop: "+ stop);

        //Μεταβλητή που συγκεντρώνει το πλήθος των matches
        int matchCount = 0;
        for (int j = start; j < stop; j++) {
			int i;
      		for (i = 0; i < m && pattern[i] == text[i + j]; i++);
				if (i >= m) {
	         		match[j] = '1';
					matchCount++;
                }        
        }
        lock.lock();
        try{
            totalMatches[index] = totalMatches[index] + matchCount;
        }finally{
            lock.unlock();
        }
        // System.out.println("Thread "+index+" matches: "+ totalMatches[index]);

    }

}


//Execution Results για το αρχείο tentimes.txt
//7311 4646001 9284691 13923381 18562071 23200761 27839451 32478141 37116831 41755521 
//Time to compute = 0,329000 seconds
//Πολύ μικρότερος συγκριτικά με το ακολουθιακό Brute Force 
//Total matches 10

//Execution Results για το αρχείο ten3times.txt
//7311 4646001 9284691 13923381 18562071 23200761 27839451 32478141 37116831 41755521 46394211 51032901 55671591 60310281 64948971 69587661 74226351 78865041 83503731 88142421 92781111 97419801 102058491 106697181 111335871 115974561 120613251 125251941 129890631 134529321 
//Time to compute = 0,892000 seconds
//Total matches 30
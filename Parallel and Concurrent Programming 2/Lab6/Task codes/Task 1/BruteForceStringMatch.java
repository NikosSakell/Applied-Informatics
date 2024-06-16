import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*; 


public class BruteForceStringMatch {

    public static void main(String args[]) throws IOException {

        long startTime = System.currentTimeMillis();
    
        if (args.length != 2) {
			System.out.println("BruteForceStringMatch  <file name> <pattern>");
			System.exit(1);
        }

        String fileString = new String(Files.readAllBytes(Paths.get(args[0])));//, StandardCharsets.UTF_8);
        char[] text = new char[fileString.length()]; 
        int n = fileString.length();
        for (int i = 0; i < n; i++) { 
            text[i] = fileString.charAt(i); 
        } 
 
        String patternString = new String(args[1]);                                                
        char[] pattern = new char[patternString.length()]; 
        int m = patternString.length(); 
        for (int i = 0; i < m; i++) { 
            pattern[i] = patternString.charAt(i); 
        }

        int matchLength = n - m;
        char[] match = new char[matchLength+1]; 
        for (int i = 0; i <= matchLength; i++) { 
            match[i] = '0'; 
        }
        /*ArrayList<Integer> match = new ArrayList<Integer>(); */
        int matchCount = 0;
        for (int j = 0; j < matchLength; j++) {
			int i;
      		for (i = 0; i < m && pattern[i] == text[i + j]; i++);
				if (i >= m) {
	         		match[j] = '1';
					matchCount++;
                }        
        }

        long endTime = System.currentTimeMillis();

        for (int i = 0; i < matchLength; i++) { 
            if (match[i] == '1') System.out.print(i+" ");
        }
        System.out.println();
        System.out.printf("Time to compute = %f seconds\n", (double) (endTime - startTime) / 1000);  
        System.out.println("Total matches " + matchCount);
        
   }
}


//Execution Results για το αρχείο tentimes.txt:
//7311 4646001 9284691 13923381 18562071 23200761 27839451 32478141 37116831 41755521 
//Time to compute = 0,651000 seconds
//Total matches 10

//Execution Results για το αρχείο ten3times.txt
//7311 4646001 9284691 13923381 18562071 23200761 27839451 32478141 37116831 41755521 46394211 51032901 55671591 60310281 64948971 69587661 74226351 78865041 83503731 88142421 92781111 97419801 102058491 106697181 111335871 115974561 120613251 125251941 129890631 134529321 
//Time to compute = 1,586000 seconds
//Total matches 30

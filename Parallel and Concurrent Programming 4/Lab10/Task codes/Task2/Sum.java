public class Sum {
	private static int aNum;
	private static double result;
	
	public Sum (int init) {
		aNum = init;
		result = 0;
	}

	public synchronized void addTo(double toAdd) {
		result += toAdd;
	}

	public synchronized void printResult () {
		double pi = result;

        System.out.printf("computed pi = %22.20f\n" , pi);
        System.out.printf("difference between estimated pi and Math.PI = %22.20f\n", Math.abs(pi - Math.PI));
	    System.out.println("Result =" + result);
	}
	
	public synchronized String printInit () {
	    return String.valueOf(aNum);
	}
        
}

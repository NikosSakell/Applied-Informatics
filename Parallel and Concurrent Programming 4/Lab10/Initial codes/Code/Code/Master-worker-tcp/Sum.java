public class Sum {
	private static int aNum;
	private static int result;
	
	public Sum (int init) {
		aNum = init;
		result = 0;
	}

	public synchronized void addTo(int toAdd) {
		result += toAdd;
	}

	public synchronized void printResult () {
	    System.out.println("Result =" + result);
	}
	
	public synchronized String printInit () {
	    return String.valueOf(aNum);
	}
        
}

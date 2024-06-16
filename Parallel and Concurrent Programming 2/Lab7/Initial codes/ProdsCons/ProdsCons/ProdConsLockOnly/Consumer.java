public class Consumer extends Thread {
	private Buffer buff;
	private int scale;

	// Constructor
	public Consumer(Buffer buff2, int s) {
		this.buff = buff2;
		this.scale = s;
	}

	// Consumer runs for ever
	public void run() {
		int value;
		while (true) {
			try {
				sleep((int)(Math.random()*scale));
			} catch (InterruptedException e) { }
			value = buff.get();
		}
	}
}

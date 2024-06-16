public class BufferSPSC
{
	private int[] contents;
	// private boolean bufferEmpty = true;
	// private boolean bufferFull = false;
	private int size;
	private int counter = 0; //front και back δεν χρειάζονται

	// Constructor
	public BufferSPSC(int s) {
	this.size = s;
	contents = new int[size];
	for (int i=0; i<size; i++)
		contents[i] = 0;
		// this.front = 0;
		// this.back = -1;
	}

	// Put an item into buffer
	public synchronized void put(int data)
	{
		while (counter == size) {
			try {
				wait();
			} catch (InterruptedException e) {}
		}
		// back = (back + 1) % size;
		contents[counter] = data;
		counter++;
		System.out.println("Item " + data + " added in loc " + size + ". Count = " + counter);
		// bufferEmpty = false;
		if (counter==size)
		{
			// bufferFull = true;
			System.out.println("The buffer is full");
		}
		//buffer was empty
		if (counter == 1) notifyAll();
	}

	// Get an item from bufffer
	public synchronized int get()
	{
		while (counter == 0) {
			try {
				wait();
			}
			catch (InterruptedException e) {}
		}
		int data = contents[counter];
		counter--;
		System.out.println("Item " + data + " removed from loc " + size + ". Count = " + counter);	
		// front = (front + 1) % size;	
		// bufferFull = false;
		if (counter==0) 
		{
			// bufferEmpty = true;
			System.out.println("The buffer is empty");
		}
		//buffer was full
		if (counter == size-1) notifyAll();
		return data;
	}
}
	
//1.1 a)

public class BufferSize1
{
	private int contents;
	private boolean bufferEmpty = true;
	private boolean bufferFull = false;
	private int size;
	//Τα front και back δεν χρειάζονται
	// private int front, back, counter = 0;

	// Constructor
	public BufferSize1(int s) {
	this.size = s;
	contents = 0;
	// for (int i=0; i<size; i++)
	// 	contents[i] = 0;
		// this.front = 0;
		// this.back = -1;
	}

	// Put an item into buffer
	public synchronized void put(int data)
	{
		while (bufferFull) {
			try {
				wait();
			} catch (InterruptedException e) {}
		}
		// back = (back + 1) % size;
		contents = data;
		// counter++;
		System.out.println("Item " + data + " added in loc " + size);
		bufferEmpty = false;
		// if (counter==size)
		// {
			bufferFull = true;
			System.out.println("The buffer is full");
		// }
		//buffer was empty
		// if (counter == 1) 
		notifyAll();
	}

	// Get an item from bufffer
	public synchronized int get()
	{
		while (bufferEmpty) {
			try {
				wait();
			}
			catch (InterruptedException e) {}
		}
		int data = contents;
		// counter--;
		System.out.println("Item " + data + " removed from loc " + size);	
		// front = (front + 1) % size;	
		bufferFull = false;
		// if (counter==0) 
		// {
			bufferEmpty = true;
			System.out.println("The buffer is empty");
		// }
		//buffer was full
		// if (counter == size-1) 
		notifyAll();
		return data;
	}
}

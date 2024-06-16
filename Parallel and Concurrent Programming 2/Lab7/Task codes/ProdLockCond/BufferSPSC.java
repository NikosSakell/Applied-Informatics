import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;

//1.2 για κλειδώματα και μεταβλητές συνθήκης με πλήθος Producers = Consumers = 1

public class BufferSPSC
{
	private int[] contents;
	private int size;
	//Δεν χρειάζονται τα back και front
	// private int front, back;
	//Χρησιμοποίησα τον counter ως δείκτη για τον πίνακα contents
	private int counter = 0;
	private Lock lock = new ReentrantLock();
	private Condition bufferFull = lock.newCondition();
	private Condition bufferEmpty = lock.newCondition();

	// Constructor
	public BufferSPSC(int s) {
	this.size = s;
	contents = new int[size];
	for (int i=0; i<size; i++)
		contents[i] = 0;
		// this.front = 0;
		// this.back = size - 1;
	}

	// Put an item into buffer
	public void put(int data) {

		lock.lock();
			try {
				while (counter == size) {
				System.out.println("The buffer is full");
				try {
					bufferFull.await();
				} catch (InterruptedException e) { }
			}
			// back = (back + 1) % size;
			contents[counter] = data;
			counter++;
			System.out.println("Prod " + Thread.currentThread().getName() + " No "+ data + /*" Loc " + back +*/ " Count = " + counter);
			//buffer was empty
			if (counter == 1) bufferEmpty.signalAll();
		} finally {
			lock.unlock() ;
		}
	}

	// Get an item from bufffer
	public int get() {
		int data = 0;

		lock.lock();
		try {
			while (counter == 0) {
				System.out.println("The buffer is empty");
				try {
					bufferEmpty.await();
				} catch (InterruptedException e) { }
			}
			data = contents[counter];
			System.out.println("  Cons " + Thread.currentThread().getName() + " No "+ data + /*" Loc " + front + */ " Count = " + (counter-1));
			// front = (front + 1) % size;
			counter--;
			//buffer was full
			if (counter == size-1) bufferFull.signalAll();
		} finally {
			lock.unlock() ;
		}
		return data;
	}
}
	
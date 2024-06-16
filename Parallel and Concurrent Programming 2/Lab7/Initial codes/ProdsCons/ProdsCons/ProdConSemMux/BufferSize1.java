import java.util.concurrent.Semaphore;

//1.1 α) Κώδικας για μέγεθος buffer 1 και χρήση Semaphores

public class BufferSize1
{
	//Δεν χρειάζεται πλέον πίνακας για τα contents γιατί ο buffer
	//έχει μόνο ένα αντικείμενο τη φορά
	private int content = 0;
	private int size;
	//Δεν χρειάζονται τα front και back
	// private int front, back;
	//Μπορεί να γίνει και χωρίς το counter αφού ο buffer θα είναι
	//εναλλάξ μια φορά γεμάτος και μια άδειος
	// private int counter = 0;
	//Δεν χρειαζόμαστε αμοιβαίο αποκλεισμό
	// private Semaphore mutex = new Semaphore(1);
	private Semaphore bufferFull = new Semaphore(0);
	private Semaphore bufferEmpty; 

	// Constructor
	public BufferSize1(int s) {
	this.size = s;
	
	//Η for είναι περιττή εφόσον ορίσαμε ήδη το content=0
	// contents = new int[size];
	// for (int i=0; i<size; i++)
		// contents[i] = 0;
		// this.front = 0;
		// this.back = size-1;	
	this.bufferEmpty = new Semaphore(size);
	}

	// Put an item into buffer
	public void put(int data) {
		try {
			bufferEmpty.acquire();
		} catch (InterruptedException e) { }

		//Η μεταβλητή mutex δεν χρειάζεται
		// try {
		// 	mutex.acquire();
		// } catch (InterruptedException e) { }

		//Δεν χρειάζεται κάποιος υπολογισμός για κάποιο back 
		// back = (back + 1) % size;
		//Τα δεδομένα περνάνε κατευθείαν στο content 
		content = data;
		// counter++;
		System.out.println("Prod " + Thread.currentThread().getName() + " No "+ data + " Loc " + size);
		// if (counter == size)
		 System.out.println("The buffer is full");
		// mutex.release(); 
		bufferFull.release(); 
	}

	// Get an item from bufffer
	public int get() {
		int data = 0;
		try {
			bufferFull.acquire();
		} catch (InterruptedException e) { }

		//Η μεταβλητή mutex δεν χρειάζεται
		// try {
		// 	mutex.acquire();
		// } catch (InterruptedException e) { }

		//Το μοναδικό content περνάει στα δεδομένα
		data = content;
		System.out.println("  Cons " + Thread.currentThread().getName() + " No "+ data + " Loc " + size);
		//Δεν χρειάζεται κάποιος υπολογισμός για το front
        // front = (front + 1) % size;
		// counter--;	
		// if (counter == 0) 
		System.out.println("The buffer is empty");	
		// mutex.release();		
		bufferEmpty.release();
		return data;
	}
}

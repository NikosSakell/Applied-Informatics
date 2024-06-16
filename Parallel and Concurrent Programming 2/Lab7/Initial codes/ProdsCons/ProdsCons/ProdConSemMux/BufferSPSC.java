import java.util.concurrent.Semaphore;

//1.2 για 1 Producer 1 Consumer και χρήση Semaphores

//Νομίζω δεν χρειάζεται κάποια αλλαγή αν και τα αποτελέσματα
//που παίρνω αν βγάλω το mutex είναι τα ίδια που παίρνω και με αυτό

public class BufferSPSC
{
	//To contents έγινε μεταβλητή, ο πίνακας δεν έχει νόημα νομίζω εφόσον ο buffer
	//έχει μέγεθος 1
	private int contents = 0;
	private int size;
	// private int front, back;
	private int counter = 0;
	//Ο buffer mutex δεν χρειάζεται γιατί δεν έχουμε πολλαπλούς
	//παραγωγούς και πολλαπλούς καταναλωτε΄ς αλλά 1 και 1
	// private Semaphore mutex = new Semaphore(1);
	private Semaphore bufferFull = new Semaphore(0);
	private Semaphore bufferEmpty; 

	// Constructor
	public BufferSPSC(int s) {
	this.size = s;
	//Η for δεν αρχικοποιεί κάτι εφόσον contents = 0 οπότε φεύγει
	// contents = new int[size];
	// for (int i=0; i<size; i++)
	// 	contents[i] = 0;
		// this.front = 0;
		// this.back = size-1;	
		this.bufferEmpty = new Semaphore(size);
	}

	// Put an item into buffer
	public void put(int data) {
		try {
			bufferEmpty.acquire();
		} catch (InterruptedException e) { }

		// Δεν χρειαζόμαστε buffer mutex
		// try {
		// 	mutex.acquire();
		// } catch (InterruptedException e) { }
		
		//Δεν χρειαζόμαστε back και front
		// back = (back + 1) % size;
		//Δεν χρειάζεται πίνακας χρησιμοποιούμε τη μεταβλητή contents
		contents = data;
		counter++;
		//Δεν χρειάζεται κάποια ένδειξη για τη θέση μέσα στο buffer οπότε μπορούμε να βάλουμε για loc πχ το size
		System.out.println("Prod " + Thread.currentThread().getName() + " No "+ data + " Loc " + size + " Count = " + counter);
		if (counter == size) System.out.println("The buffer is full");
		// mutex.release(); 
		bufferFull.release(); 
	}

	// Get an item from bufffer
	public int get() {
		int data = 0;
		try {
			bufferFull.acquire();
		} catch (InterruptedException e) { }

		// Δεν χρειαζόμαστε buffer mutex
		// try {
		// 	mutex.acquire();
		// } catch (InterruptedException e) { }
		
		//Δεν χρειάζεται πίνακας χρησιμοποιούμε τη μεταβλητή contents
		data = contents;
		
		//Δεν χρειάζεται κάποια ένδειξη για τη θέση μέσα στο buffer οπότε μπορούμε να βάλουμε για loc πχ το size
		System.out.println("  Cons " + Thread.currentThread().getName() + " No "+ data + " Loc " + size + " Count = " + (counter-1));
        // front = (front + 1) % size;
		counter--;	
		if (counter == 0) System.out.println("The buffer is empty");	
		// mutex.release();		
		bufferEmpty.release();
		return data;
	}
}
import java.util.concurrent.Semaphore;
import java.util.ArrayList;

//1.1 β) για φαινομενικά άπειρο μέγεθος buffer και Semaphores

public class BufferSizeMAX
{
	//Δημιουργία δυναμικού array
	private ArrayList<Integer> contents =  new ArrayList<>();
	//Η μεταβλητή size δεν χρειάζεται πλεον
	// private int size;
	private int front, back;
	private int counter = 0;
	private Semaphore mutex = new Semaphore(1);
	private Semaphore bufferFull = new Semaphore(0);
	private Semaphore bufferEmpty; 

	//Constructor
	public BufferSizeMAX(int s) {
	
    //Η αρχικοποίηση της λίστας δεν χρειάζεται πλέον
	//contents = new int[size];
	//for (int i=0; i<size; i++)
		//contents[i] = 0;
		this.front = 0;
		this.back = s-1;	
		this.bufferEmpty = new Semaphore(s);
	}

	// Put an item into buffer
	public void put(int data) {
		try {
			bufferEmpty.acquire();
		} catch (InterruptedException e) { }
		try {
			mutex.acquire();
		} catch (InterruptedException e) { }
		back = (back + 1); //% size;
		//Αλλαγή της εντολής ανάθεσης των δεδομένων για το δυναμικό Array
		contents.add(data);
		counter++;
		System.out.println("Prod " + Thread.currentThread().getName() + " No "+ data + " Loc " + back + " Count = " + counter);
		// if (counter == size) System.out.println("The buffer is full");
		mutex.release(); 
		bufferFull.release(); 
	}

	// Get an item from bufffer
	public int get() {
		int data = 0;
		try {
			bufferFull.acquire();
		} catch (InterruptedException e) { }
		try {
			mutex.acquire();
		} catch (InterruptedException e) { }
		data = contents.get(front);
		System.out.println("  Cons " + Thread.currentThread().getName() + " No "+ data + " Loc " + front + " Count = " + (counter-1));
        front = (front + 1); //% size;
		counter--;	
		if (counter == 0) System.out.println("The buffer is empty");	
		mutex.release();		
		bufferEmpty.release();
		return data;
	}
}
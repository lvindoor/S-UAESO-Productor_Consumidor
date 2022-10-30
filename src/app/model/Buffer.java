package app.model;

import java.util.concurrent.Semaphore;

public class Buffer {
	
	private int item;
	// Con initialized with 0 permits
	// to ensure put() executes first
	static Semaphore Con = new Semaphore(0);
	static Semaphore Prod = new Semaphore(1);

	// to get an item from buffer
	void get() {
		try {
			// Before consumer can consume an item,
			// it must acquire a permit from Con
			Con.acquire();
		} catch (InterruptedException e) {
			System.out.println("InterruptedException caught");
		}

		// consumer consuming an item
		System.out.println("Consumer consumed item: " + item);

		// After consumer consumes the item,
		// it releases Prod to notify producer
		Prod.release();
	}

	// to put an item in buffer
	void put(int item) {
		try {
			// Before producer can produce an item,
			// it must acquire a permit from Prod
			Prod.acquire();
		} catch (InterruptedException e) {
			System.out.println("InterruptedException caught");
		}

		// producer producing an item
		this.item = item;

		System.out.println("Producer produced item: " + item);

		// After producer produces the item,
		// it releases Con to notify consumer
		Con.release();
	}
}

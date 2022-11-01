package app.model;

import java.util.Random;

public class Producer implements Runnable {

	private Buffer buffer;
	private int quantity;

	public Producer(Buffer b, int q) {
		this.buffer = b;
		this.quantity = q;
		new Thread(this, "Producer").start();
	}

	public void run() {
		while(true) {
			Random ran = new Random();
			int x = ran.nextInt(quantity);
			buffer.put(x);
		}			
	}
}
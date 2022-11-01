package app.model;

import java.util.Random;

public class Consumer implements Runnable {

	private Buffer buffer;
	private int quantity;

	public Consumer(Buffer b, int q) {		
		this.buffer = b;
		this.quantity = q;
		new Thread(this, "Consumer").start();
	}

	public void run() {
		
		while(true) {
			Random ran = new Random();
			boolean x = ran.nextBoolean();
			if(x) {
				// Obten los items a consumir
				buffer.get();
			} else {
				buffer.Con.release();
			}			
		}
	}
}
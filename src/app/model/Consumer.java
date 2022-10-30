package app.model;

public class Consumer implements Runnable {

	private Buffer buffer;

	public Consumer(Buffer b) {
		this.buffer = b;
		new Thread(this, "Consumer").start();
	}

	public void run() {
		for (int i = 0; i < 5; i++)
			// consumer get items
			buffer.get();
	}
}
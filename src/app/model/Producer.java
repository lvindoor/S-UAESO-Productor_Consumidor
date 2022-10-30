package app.model;

public class Producer implements Runnable {

	private Buffer buffer;

	public Producer(Buffer b) {
		this.buffer = b;
		new Thread(this, "Producer").start();
	}

	public void run() {
		for (int i = 0; i < 5; i++)
			buffer.put(i);
	}
}
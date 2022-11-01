package app.model;

import java.awt.Color;
import java.util.concurrent.Semaphore;

import app.Main;

public class Buffer {

	private int delay;
	private int item;

	static Semaphore Con = new Semaphore(0);
	static Semaphore Prod = new Semaphore(1);

	// Constructor
	public Buffer(int delay) {
		this.delay = delay;
	}

	void get() {
		try {
			Con.acquire();
		} catch (InterruptedException e) {
			System.out.println("InterruptedException caught");
		}

		System.out.println("Consumidor ha consumido el item: " + (item + 1));
		Main.view.paintRow(Color.gray, item);
		createDelay();

		Prod.release();
	}

	void put(int item) {
		try {
			Prod.acquire();
		} catch (InterruptedException e) {
			System.out.println("InterruptedException caught");
		}

		this.item = item;

		System.out.println("Productor ha producido el item: " + (item + 1));
		Main.view.paintRow(Color.orange, item);
		createDelay();

		Con.release();
	}

	public void createDelay() {
		try {
			Thread.sleep(this.delay * 1000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}

}

package app;
import app.model.Buffer;
import app.model.Consumer;
import app.model.Producer;

public class Main {

	public static void main(String[] args) {
		 // creating buffer queue
        Buffer b = new Buffer();
        // starting consumer thread
        new Consumer(b);
        // starting producer thread
        new Producer(b);
	}
}



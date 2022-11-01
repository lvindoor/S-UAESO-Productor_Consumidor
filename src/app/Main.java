package app;

import java.io.IOException;

import app.model.Buffer;
import app.model.Configuration;
import app.model.Consumer;
import app.model.Producer;
import app.view.Process;

public class Main {
	
	public static Process view;
	
	public static void main(String[] args) {
		
		// Creamos la configuracion por defecto
		Configuration conf = new Configuration(25, 25, 1);
		
		// Arracamos la vista
		view = new Process();
		try {
			view.create(conf);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void run(Configuration conf) {
		// Creamos el buffer
        Buffer b = new Buffer(conf.getDelayTime());
        // Arrancamos el Consumidor
        new Consumer(b,conf.getQuantityToConsume());
        // Arrancamos el Productor
        new Producer(b,conf.getQuantityToProduce());
	}
}



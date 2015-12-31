package pl.cryptnet.experimental.basics.test1;

import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SpamProducer extends Thread {
	private static final Logger logger = LogManager.getLogger(SpamProducer.class);
	private LinkedBlockingQueue<String> queue;
	private int lifeSpan;
	Random r = new Random();
	
	public SpamProducer(int lifeSpan, LinkedBlockingQueue<String> toWrite) {
		queue = toWrite;
		this.lifeSpan = lifeSpan;
	}
	
	public void run(){
		for(int i = 0; i < lifeSpan; i++){
			send();
		}
		
	}
	
	private void send(){
		try {
			queue.put(String.format("My random number %d", r.nextInt(100)));
			Thread.sleep(500 * r.nextInt(5));
		} catch (InterruptedException e) {
			logger.fatal(e.getMessage());
			Thread.currentThread().interrupt();
		}		
	}
	
}

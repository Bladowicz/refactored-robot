package pl.cryptnet.experimental.basics.test1;

import java.util.concurrent.LinkedBlockingQueue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SpamConsumer extends Thread{
	private static final Logger logger = LogManager.getLogger(SpamConsumer.class);
	LinkedBlockingQueue<String> queue;
	
	public SpamConsumer(LinkedBlockingQueue<String> toWrite) {
		queue = toWrite;
	}

	public void run(){
		try {
			for(int i = 0; i < 50; i++)
			logger.info(queue.take());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			Thread.currentThread().interrupt();
		}
	}
}

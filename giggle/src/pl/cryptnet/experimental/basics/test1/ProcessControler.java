package pl.cryptnet.experimental.basics.test1;

import java.util.concurrent.LinkedBlockingQueue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 * @author gbaranowski
 *
 */
public class ProcessControler {
	private static final Logger logger = LogManager.getLogger(ProcessControler.class);
	private LinkedBlockingQueue toWrite;
	private SpamProducer[] producers;
	private SpamConsumer[] consumers;
	
	
	public ProcessControler() {
		this(10, 10);

	}
	
	public ProcessControler(int producerCount, int producerLifespan) {
		toWrite = new LinkedBlockingQueue(1024);
		logger.info(String.format("Producers : [%d] with lifespan : [%d]", producerCount, producerLifespan));
		createProducers(producerCount, producerLifespan);
		createConsumers();
	}
	
	private void createConsumers() {
		// TODO Auto-generated method stub
		
	}

	private void createProducers(int producerCount, int producerLifespan){
		producers = new SpamProducer[producerCount];
		for(int i=0; i<producerCount; i++){
			producers[i] = new SpamProducer(producerLifespan, toWrite);
		}
	}

}

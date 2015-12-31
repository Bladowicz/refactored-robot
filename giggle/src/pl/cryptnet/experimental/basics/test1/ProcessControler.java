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
	private LinkedBlockingQueue<String> toWrite;
	private SpamProducer[] producers;
	private SpamConsumer[] consumers;
	
	
	public ProcessControler(ProcessControlerConfiguration config) {
		toWrite = new LinkedBlockingQueue<>(config.getQueueLength());
		logger.info(String.format("Producers : [%d] with lifespan : [%d]", config.getProducerCount(), config.getProducerLifespan()));
		createProducers(config.getProducerCount(), config.getProducerLifespan());
		createConsumers(config.getConsumerCount());
	}
	
	private void createConsumers(int consumerCount) {
		consumers = new SpamConsumer[consumerCount];
		for(int i=0; i<consumerCount; i++){
			consumers[i] = new SpamConsumer(toWrite);
		}
	}

	private void createProducers(int producerCount, int producerLifespan){
		producers = new SpamProducer[producerCount];
		for(int i=0; i<producerCount; i++){
			producers[i] = new SpamProducer(producerLifespan, toWrite);
		}
	}

	public void start() {
		for (SpamConsumer consumer : consumers)
			consumer.start();
		for (SpamProducer producer : producers)
			producer.start();
	}
}

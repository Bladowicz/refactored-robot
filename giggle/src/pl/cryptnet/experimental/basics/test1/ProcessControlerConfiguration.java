package pl.cryptnet.experimental.basics.test1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ProcessControlerConfiguration {
	private static final Logger logger = LogManager.getLogger(ProcessControlerConfiguration.class);
	private Properties properties = new Properties();
	
	private int producerCount;
	private int producerLifespan;
	private int consumerCount;
	private int queueLength;
	
	
	public ProcessControlerConfiguration(String configFilePath) {
		try (FileInputStream fileInput = new FileInputStream(new File(configFilePath));) {
			properties.load(fileInput);
		} catch (FileNotFoundException e) {
			logger.fatal(String.format("File %s was not found.", configFilePath));
			System.exit(1);
		} catch (IOException e) {
			logger.fatal(String.format("File %s was not accessible.", configFilePath));
			System.exit(1);
		}
		getValuesFromFile();
	}

	private void getValuesFromFile() {
		producerCount = Integer.parseInt(properties.getProperty("producerCount", "5"));
		producerLifespan = Integer.parseInt(properties.getProperty("producerLifespan", "5"));
		consumerCount = Integer.parseInt(properties.getProperty("consumerCount", "5"));
		queueLength = Integer.parseInt(properties.getProperty("queueLength", "5"));
		
	}
	
	public int getProducerCount() {
		return producerCount;
	}
	public int getProducerLifespan() {
		return producerLifespan;
	}
	public int getConsumerCount() {
		return consumerCount;
	}
	public int getQueueLength() {
		return queueLength;
	}
}

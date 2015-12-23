package pl.bpo.atd.gb;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class RePacker {
	private static final Logger logger = LogManager.getLogger(RePacker.class);
	
	
	public RePacker(String configFilePath) {
		super();
		logger.info("Starting repackaging");
		Config conf = new Config(configFilePath);

		
	}

	public void run() {
		// TODO Auto-generated method stub
		
	}

}

package pl.bpo.atd.gb;

import org.apache.logging.log4j.Logger;

import java.io.File;
import java.util.Arrays;

import org.apache.logging.log4j.LogManager;

public class RePacker {
	private static final Logger logger = LogManager.getLogger(RePacker.class);
	
	
	public RePacker(String configFilePath) {
		super();
		logger.info("Starting repackaging");
		Config conf = new Config(configFilePath);
		File folder = new File(conf.getRawDataFolder());
		System.out.println(folder.listFiles());

		
	}

	public void run() {
		// TODO Auto-generated method stub
		
	}

}

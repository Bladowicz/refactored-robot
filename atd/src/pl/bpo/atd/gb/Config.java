package pl.bpo.atd.gb;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Config {
	private static final Logger logger = LogManager.getLogger(Config.class);
	
	private Properties properties  = new Properties();
	
	public Config(String configFilePath) {
		try(FileInputStream fileInput = new FileInputStream(new File(configFilePath));){
			properties.load(fileInput);		
		} catch (FileNotFoundException e) {
			logger.fatal(String.format("File %s was not found.", configFilePath));
			System.exit(1);
		} catch (IOException e) {
			logger.fatal(String.format("File %s was not accessible.", configFilePath));
			System.exit(1);
		}
		System.out.println(properties.getProperty("rawdatafolder"));
	}

}

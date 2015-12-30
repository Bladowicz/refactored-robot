package pl.bpo.atd.gb;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Handler;
import java.util.regex.Pattern;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RePacker {
	private static final Logger logger = LogManager.getLogger(RePacker.class);
	private List<FileRePacker> fileRePackers = new ArrayList<>();
	File[] files;
	File folder;
	Pattern datePatt;

	public RePacker(String configFilePath) {
		super();
		logger.info("Starting repackaging");
		Config conf = new Config(configFilePath);
		folder = new File(conf.getRawDataFolder());
		logger.debug(String.format("Looking for files in folder %s", conf.getRawDataFolder()));
		datePatt = Pattern.compile(conf.getFilePattern());
		logger.debug(String.format("Looking for files with patter %s", conf.getFilePattern()                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                          ));
		findFiles();
		logger.debug(String.format("Found %d files", files.length));
	}
	
	public void run(){
		createWorkers();
		runFewWorkers();		
	}

	private void findFiles() {
		files = folder.listFiles(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return datePatt.matcher(name).matches();
			}
		});
	}

	private void createWorkers() {
		for (File f : files) {
			fileRePackers.add(new FileRePacker(f, "/home/gbaranowski/VWT_env/modelData.rePacked/"));
		}

	}

	private void runFewWorkers() {
		for (FileRePacker f : fileRePackers) {
			f.start();
		}

	}
	
	public void end() {
		for(FileRePacker fr : fileRePackers){
			try {
				fr.join();
			} catch (InterruptedException e) {
				logger.warn(String.format("Thread %s was killed", fr.getName()));

			}
		}
		
		FileRePacker.end();
	}


}

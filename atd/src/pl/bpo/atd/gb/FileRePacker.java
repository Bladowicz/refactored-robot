package pl.bpo.atd.gb;

import java.io.File;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FileRePacker  extends Thread  {
	private static final Logger logger = LogManager.getLogger(FileRePacker.class);
	File target;
	public FileRePacker(File f) {
		target = f;
		logger.info(String.format("File %s will be repacked", f));
		// TODO Auto-generated constructor stub
	}
	
	public void run(){
		logger.info(String.format("File %s is now proccessed", target));
		
	}
}

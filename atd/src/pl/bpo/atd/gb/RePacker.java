package pl.bpo.atd.gb;

import java.io.File;
import java.io.FilenameFilter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RePacker {
	private static final Logger logger = LogManager.getLogger(RePacker.class);
//	private Date date;
	private List<FileRePacker> fileRePackers = new ArrayList<>();
	
	public RePacker(String configFilePath) {
		super();
		logger.info("Starting repackaging");
		Config conf = new Config(configFilePath);
		File folder = new File(conf.getRawDataFolder());

		Pattern datePatt = Pattern.compile("modelData_([0-9]{4})-([0-9]{2})-([0-9]{2}).gz");

		
		File[] files = folder.listFiles(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {

				return datePatt.matcher(name).matches();
			}
		});
		for (File f : files){
			
			
//			logger.info(String.format("Full path: %s", m.matches()));
//			logger.info(String.format("Full path: %s", f));
			fileRePackers.add(new FileRePacker(f));
//			logger.info(String.format("Full path: %s", f.getName()));
//			logger.info(String.format("Full path: %s", f.getParent()));
//			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//			try {
//				date = dateFormat.parse( f.getName());
//			} catch (ParseException e) {
//				logger.fatal(String.format("File %s could not be parsed to date", f.getName()));
//				System.exit(1);
//			}
//			logger.info(String.format("Full path: %s", date.getTime()/1000));
		
			
		}
		for (FileRePacker f : fileRePackers){
			f.start();
		}
		
	}

	public void run() {
		// TODO Auto-generated method stub
		
	}

}

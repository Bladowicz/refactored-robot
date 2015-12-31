package pl.bpo.dst.model.repacker;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RePacker {
	private static final Logger logger = LogManager.getLogger(RePacker.class);
	Path destination;
	Path workDir;
	private static Map<String, BufferedWriter> handlers = new HashMap<>();

	public RePacker(String configFilePath) throws IOException {
		logger.info("Starting repackaging");
		Config conf = new Config(configFilePath);
		destination = Paths.get(conf.getDestination());
		workDir = Paths.get(conf.getWorkDir());
		readInput();
		finish();
	}

	private void readInput() {
		BufferedReader isReader = new BufferedReader(new InputStreamReader(System.in));
		isReader.lines().forEach(line -> {
			try {
				consumeLine(line);
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
	}

	private void consumeLine(String line) throws IOException {
		Long tsl = Long.parseLong(line.substring(20, 30));
		Timestamp ts = new Timestamp(tsl * 1000);
		String S = new SimpleDateFormat("yyyyMMddHH").format(ts);

		BufferedWriter wr = handlers.get(S);
		if (wr == null) {
			Path p = workDir.resolve(String.format("#%s", S));
			logger.info(String.format("Creating path %s", p));
			wr = new BufferedWriter(new OutputStreamWriter(new GZIPOutputStream(Files.newOutputStream(p))));
			handlers.put(S, wr);
		}
		wr.write(line + "\n");
	}

	private void finish() throws IOException {
		for (Map.Entry<String, BufferedWriter> entry : handlers.entrySet()) {
			BufferedWriter wr = entry.getValue();
			String id = entry.getKey();
			String filename = String.format("modelData.reLog.%s-%s-%s-%s.gz", id.substring(0, 4), id.substring(4,6), id.substring(6,8), id.substring(8,10));
			Path finalFile = destination.resolve(filename);
			Path p = workDir.resolve(String.format("#%s", id));
			if (Files.exists(finalFile)) {
				logger.info(String.format("Final destination %s exists. Rewriting its content", finalFile));
				new BufferedReader(new InputStreamReader(new GZIPInputStream(Files.newInputStream(finalFile)))).lines()
						.forEach(line -> {
							try{
							wr.write(line + "\n");
							}catch(IOException e){
								e.printStackTrace();
							}
						});
			}

			wr.close();
			Files.move(p, finalFile, REPLACE_EXISTING);

		}

	}

}

package pl.bpo.dst.model.repacker;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
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

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FileRePacker extends Thread {
	private static final Logger logger = LogManager.getLogger(FileRePacker.class);
	Path target;
	Path destination;
	private static Map<String, BufferedWriter> handlers = new HashMap<>();

	public FileRePacker(File f, String d) {
		target = f.toPath();
		destination = Paths.get(d);
		logger.debug(String.format("File %s will be repacked", f));
	}
//
//	public void run() {
//		logger.info(String.format("File %s is now proccessed", target));
//		processFile(target);
//		logger.info(String.format("File %s is now done", target));
//	}
//
//	private void processFile(Path filename) {
//		try (BufferedReader br = new BufferedReader(
//				new InputStreamReader(new GZIPInputStream(Files.newInputStream(filename))))) {
//			br.lines().forEach(line -> checkLine(line));
//		} catch (IOException e) {
//			logger.fatal(e.toString());
//			e.printStackTrace();
//		} catch (NumberFormatException e) {
//			logger.fatal(e.toString());
//			e.printStackTrace();
//		} catch (Exception e) {
//			logger.fatal(e.toString());
//			e.printStackTrace();
//		}
//
//	}
//
//	private Object checkLine(String line) throws IOException {
//		Long tsl = Long.parseLong(line.substring(20, 30));
//		Timestamp ts = new Timestamp(tsl * 1000);
//		String S = new SimpleDateFormat("yyyyMMddhh").format(ts);
//		BufferedWriter wr = handlers.get(S);
//		if (wr == null) {
//			Path p = destination.resolve(String.format("#%s", S));
//			logger.info(String.format("Creating path %s", p));
//			wr = new BufferedWriter(new OutputStreamWriter(new GZIPOutputStream(Files.newOutputStream(p))));
//			handlers.put(S, wr);
//		}
//		wr.write(line + "\n");
//		return null;
//	}
//
//	// private BufferedWriter makeWriter(Path filepath){
//	// BufferedWriter wr = null;
//	// try {
//	// if(Files.exists(filepath)){
//	// Path tmp = filepath.getParent().resolve(filepath.getFileName().toString()
//	// + "#");
//	// Files.move(filepath, tmp);
//	// wr = new BufferedWriter(new OutputStreamWriter(new GZIPOutputStream(
//	// Files.newOutputStream(filepath))));
//	//
//	// new BufferedReader(new InputStreamReader( new GZIPInputStream(
//	// Files.newInputStream(tmp)))).lines().forEach(line ->{
//	// try{
//	// wr.write(line + "\n");
//	// }catch(IOException e){
//	//
//	// }
//	//
//	// }
//	// );
//	//
//	// }else{
//	// wr = new BufferedWriter(new OutputStreamWriter(new GZIPOutputStream(
//	// Files.newOutputStream(filepath))));
//	// }
//	// } catch (IOException e) {
//	//
//	// e.printStackTrace();
//	// }
//	// return wr;
//	// }
//
//	public static void end() {
//
//		handlers.values().stream().forEach(h -> {
//			try {
//				h.close();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//
//		});
//
//	}
}

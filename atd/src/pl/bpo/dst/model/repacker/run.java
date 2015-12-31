package pl.bpo.dst.model.repacker;

import java.io.IOException;

public class run {

	public static void main(String[] args) throws IOException {
		if (args.length != 1){
			System.out.println("[ERROR] Bad args count. Do:\n./run.sh configFilePath");
			System.exit(1);
		}
		RePacker rePacker = new RePacker(args[0]);
	}

}

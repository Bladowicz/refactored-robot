package pl.bpo.atd.gb;

public class run {

	public static void main(String[] args) {
		if (args.length != 1){
			System.out.println("[ERROR] Bad args count. Do:\n./run.sh configFilePath");
			System.exit(1);
		}
		RePacker rePacker = new RePacker(args[0]);
		rePacker.run();
	}

}

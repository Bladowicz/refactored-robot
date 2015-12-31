package pl.cryptnet.experimental.basics.test1;

public class run {

	public static void main(String[] args) {
		if (args.length != 1){
			System.err.println("Bad arg count");
			System.exit(1);
		}
		ProcessControlerConfiguration config = new ProcessControlerConfiguration(args[0]);
		ProcessControler controler = new ProcessControler(config);
		controler.start();
	}

}

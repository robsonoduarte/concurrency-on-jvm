package br.com.mystudies.concurrency;

public class RaceCondition {



	/*
	 * remove the comment in volatile reserved world for see the difference in console
	 *
	 */
	private static /*volatile */ boolean done;


	public static void main(String[] args) throws InterruptedException {
		new Thread(new Runnable() {
			@Override
			public void run() {
				int i = 0;
				while (!done) {
					i++;
				}
				System.out.println("Done");
			}
		}).start();

		System.out.println("OS: " + System.getProperty("os.name"));
		Thread.sleep(2000);
		done = true;
		System.out.println("flag done set to true");
	}
}

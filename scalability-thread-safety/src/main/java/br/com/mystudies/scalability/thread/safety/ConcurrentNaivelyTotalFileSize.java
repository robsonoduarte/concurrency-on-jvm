package br.com.mystudies.scalability.thread.safety;

import static java.lang.System.nanoTime;
import static java.util.concurrent.Executors.newFixedThreadPool;
import static java.util.concurrent.TimeUnit.SECONDS;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class ConcurrentNaivelyTotalFileSize {

	public double getTotalSizeOfFile(File file) throws Exception {
		return getTotalSizeOfFilesInDir(newFixedThreadPool(100), file);
	}



	private double getTotalSizeOfFilesInDir(final ExecutorService service, final File file) throws Exception{

		if(file.isFile()) return file.length();

		double total = 0;
		final File[] children = file.listFiles();

		if(children != null){
			final List<Future<Double>> partialTotalFutures = new ArrayList<>();
			for (final File child : children) {
				partialTotalFutures.add(service.submit(() -> {
					return getTotalSizeOfFilesInDir(service, child);
				}));
			}

			for (Future<Double> ptf : partialTotalFutures){
				total += ptf.get(100,SECONDS);
			}
		}

		return total;
	}





	public static void main(String[] args) throws Exception {
		final long start = nanoTime();
		final double total = new ConcurrentNaivelyTotalFileSize().getTotalSizeOfFile(new File("F:"));
		final long end  = nanoTime();
		System.out.println("Total Size in GB: " + ((( total / 1024 ) / 1024 ) / 1024 )) ;
		System.out.println("Time taken in seconds: " + (end - start) / 1.0e9) ;
	}


	/*
	 * Exception in thread "main" java.util.concurrent.ExecutionException: java.util.concurrent.ExecutionException: java.util.concurrent.TimeoutException
	 * at java.util.concurrent.FutureTask.report(FutureTask.java:122)
	 * at java.util.concurrent.FutureTask.get(FutureTask.java:206)
	 * at br.com.mystudies.scalability.thread.safety.NaivelyConcurrentTotalFileSize.getTotalSizeOfFilesInDir(NaivelyConcurrentTotalFileSize.java:37)
	 * at br.com.mystudies.scalability.thread.safety.NaivelyConcurrentTotalFileSize.getTotalSizeOfFile(NaivelyConcurrentTotalFileSize.java:16)
	 * 
	 */

}

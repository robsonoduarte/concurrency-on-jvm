package br.com.mystudies.scalability.thread.safety;

import static java.lang.System.nanoTime;
import static java.util.concurrent.Executors.newFixedThreadPool;
import static java.util.concurrent.TimeUnit.SECONDS;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class NaivelyConcurrentTotalFileSize {

	public long getTotalSizeOfFile(File file) throws Exception {
		return getTotalSizeOfFilesInDir(newFixedThreadPool(200), file);
	}



	private long getTotalSizeOfFilesInDir(final ExecutorService service, final File file) throws Exception{

		System.out.println(file.getName());

		if(file.isFile()) return file.length();


		long total = 0;
		final File[] children = file.listFiles();

		if(children != null){
			final List<Future<Long>> partialTotalFutures = new ArrayList<>();
			for (final File child : children) {
				partialTotalFutures.add(service.submit(() -> {
					return getTotalSizeOfFilesInDir(service, child);
				}));
			}

			for (Future<Long> ptf : partialTotalFutures){
				total += ptf.get(1, SECONDS);
			}
		}

		return total;
	}





	public static void main(String[] args) throws Exception {
		final long start = nanoTime();
		final long total = new NaivelyConcurrentTotalFileSize().getTotalSizeOfFile(new File("E:/apps/"));
		final long end  = nanoTime();
		System.out.println("Total Size: " + total);
		System.out.println("Time taken: " + (end - start) / 1.0e9);
	}



}

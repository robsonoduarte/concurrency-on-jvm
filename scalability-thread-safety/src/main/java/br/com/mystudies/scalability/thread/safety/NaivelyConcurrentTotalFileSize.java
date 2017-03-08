package br.com.mystudies.scalability.thread.safety;

import static java.lang.System.nanoTime;
import static java.util.concurrent.Executors.newFixedThreadPool;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class NaivelyConcurrentTotalFileSize {

	public long getTotalSizeOfFile(File file) throws Exception {
		return getTotalSizeOfFilesInDir(newFixedThreadPool(100), file);
	}



	private long getTotalSizeOfFilesInDir(final ExecutorService service, final File file) throws Exception{

		if(file.isFile()) {
			System.out.println("IS FILE THE SIZE IS -> " + file.length());
			return file.length();
		}

		if(file.isDirectory()) {
			System.out.println("IS DIRECOTRY THE NAME IS -> " + file.getName());
			/*return file.length();*/
		}




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
				total += ptf.get(1, TimeUnit.NANOSECONDS);
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

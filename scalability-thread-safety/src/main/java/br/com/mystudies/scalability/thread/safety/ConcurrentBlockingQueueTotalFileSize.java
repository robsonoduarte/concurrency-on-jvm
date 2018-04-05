package br.com.mystudies.scalability.thread.safety;

import static java.util.concurrent.Executors.newFixedThreadPool;
import static java.util.concurrent.TimeUnit.SECONDS;

import java.io.File;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicLong;

public class ConcurrentBlockingQueueTotalFileSize {

	final private BlockingQueue<Long> fileSizes = new ArrayBlockingQueue<>(500);
	final private AtomicLong pendingFileVisits = new AtomicLong();
	final private ExecutorService service = newFixedThreadPool(100);
	
	
	public double getTotalSizeOfFile(final File file) throws InterruptedException {
		try {
			startExploreDir(file);
			long totalSize = 0;
			
			while (pendingFileVisits.get() > 0 || fileSizes.size() > 0) {
				final long size = fileSizes.poll(10, SECONDS);
				totalSize += size;
			}
			return totalSize;
		} finally {
			service.shutdown();
		}
	}

	
	private void startExploreDir(final File file) {
		pendingFileVisits.incrementAndGet();
		service.execute(() -> exploreDir(file));
	}


	private void exploreDir(final File file) {
		long fileSize = 0;
		if(file.isFile()) {
			fileSize = file.length();			
		}
		else {
			final File[] children = file.listFiles();
			if(children != null) {
				for (final File child : children) {
					if(child.isFile()) {
						fileSize += child.length();
					}else {
						startExploreDir(child);
					}
				}
			}			
		}
		
		try {
			fileSizes.put(fileSize);
		} catch (Exception e) {
			throw new RuntimeException();
		}
		pendingFileVisits.decrementAndGet();
	}
	
	
	
	public static void main(String[] args) throws Exception {		
		final long start = System.nanoTime();		
		final double total = new ConcurrentBlockingQueueTotalFileSize().getTotalSizeOfFile(new File("F:"));
		final long end = System.nanoTime();
		System.out.println("Total Size in GB: " + ((( total / 1024 ) / 1024 ) / 1024 )) ;
		System.out.println("Time Taken: " + (end - start) / 1.0e9);
	}
	
	/*
	 * Total Size in GB: 82.32213742472231 
	 * Time Taken: 6.407674982
	 */
}

package br.com.mystudies.scalability.thread.safety;

import static java.util.concurrent.Executors.newFixedThreadPool;
import static java.util.concurrent.TimeUnit.SECONDS;

import java.io.File;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicLong;

public class ConcurrentCountDownLatchTotalFileSize {

	final private ExecutorService service = newFixedThreadPool(100);;
	final private AtomicLong pendingFileVisits = new AtomicLong();
	final private AtomicLong totalSize = new AtomicLong();
	final private CountDownLatch latch = new CountDownLatch(1);
	
	public double getTotalSizeOfFile(File file) throws InterruptedException {		 
		pendingFileVisits.incrementAndGet();
		try {
			updateTotalSizeOfFilesInDir(file);
			latch.await(100, SECONDS);
			return totalSize.longValue();
		} finally {
			service.shutdown();
		}
	}
	
	private void updateTotalSizeOfFilesInDir(final File file) {
		
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
						pendingFileVisits.incrementAndGet();
						service.execute(() -> updateTotalSizeOfFilesInDir(child));
					}
				}
			}
		}
		
		totalSize.addAndGet(fileSize);
		if(pendingFileVisits.decrementAndGet() == 0) latch.countDown();
	}



	public static void main(String[] args) throws Exception {		
		final long start = System.nanoTime();		
		final double total = new ConcurrentCountDownLatchTotalFileSize().getTotalSizeOfFile(new File("F:"));
		final long end = System.nanoTime();
		System.out.println("Total Size in GB: " + ((( total / 1024 ) / 1024 ) / 1024 )) ;
		System.out.println("Time Taken: " + (end - start) / 1.0e9);
	}
	
	/*
	 * Total Size in GB: 82.32213742472231 
	 * Time Taken: 5.829283257
	 */
	
}

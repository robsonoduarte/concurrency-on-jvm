package br.com.mystudies.scalability.thread.safety;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class ConcurrentForkJoinTotalFileSize {

	private final ForkJoinPool forkJoinPool = new ForkJoinPool();
	
	
	public double getTotalSizeOfFile(File file) {
		return forkJoinPool.invoke(new FileSizeFinder(file));
	}
	
	
	
	
	private class FileSizeFinder extends RecursiveTask<Long>{
		
		private static final long serialVersionUID = -2483394014057670272L;
		
		private final File file;
		
		
		public FileSizeFinder(final File file) {
			this.file = file;
		}

		@Override
		protected Long compute() {
			long size = 0;
			if(file.isFile()) {
				size = file.length();
			}else {
				final File[] children = file.listFiles();
				if(children != null) {
					List<ForkJoinTask<Long>> tasks = new ArrayList<>();
					for (File child : children) {
						if(child.isFile()) {
							size += child.length();
						}else {
							tasks.add(new FileSizeFinder(child));
						}
					}
					
					for(final ForkJoinTask<Long> task : invokeAll(tasks)) {
						size += task.join();
					}
				}				
			}			
			return size;
		}
		
	}
	
	
	
	
	public static void main(String[] args) throws Exception {		
		final long start = System.nanoTime();		
		final double total = new ConcurrentForkJoinTotalFileSize().getTotalSizeOfFile(new File("F:"));
		final long end = System.nanoTime();
		System.out.println("Total Size in GB: " + ((( total / 1024 ) / 1024 ) / 1024 )) ;
		System.out.println("Time Taken: " + (end - start) / 1.0e9);
	}
	

}

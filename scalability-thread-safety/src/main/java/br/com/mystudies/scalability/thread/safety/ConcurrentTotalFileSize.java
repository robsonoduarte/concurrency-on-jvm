package br.com.mystudies.scalability.thread.safety;

import static java.util.concurrent.Executors.newFixedThreadPool;
import static java.util.concurrent.TimeUnit.SECONDS;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class ConcurrentTotalFileSize {

	public double getTotalSizeOfFile(File path) throws Exception{

		final ExecutorService service = newFixedThreadPool(100);
		
		try{
			long total = 0;
			final List<File> directories = new ArrayList<>();
			directories.add(path);

			while (!directories.isEmpty()) {
				final List<Future<SubDirectoriesAndSize>> partialResults = new ArrayList<>();
				for(final File directory: directories){
					partialResults.add(
						service.submit(()-> {return getTotalAndSubDirs(directory);})
					);
				}

				directories.clear();
								
				for(final Future<SubDirectoriesAndSize> partialResultFuture : partialResults){
					final SubDirectoriesAndSize subDirectoriesAndSize = 
							partialResultFuture.get(100, SECONDS);
					directories.addAll(subDirectoriesAndSize.subDirectories);
					total += subDirectoriesAndSize.size;
				}				
			}
			return total;
		}finally {
			service.shutdown();
		}
	}



	private SubDirectoriesAndSize getTotalAndSubDirs(final File file){
		long total  = 0 ;
		final List<File> subDirectories = new ArrayList<>();
		if(file.isDirectory()){
			final File[] children = file.listFiles();
			if(children != null){
				for (File child : children) {
					if(child.isFile()){
						total += child.length();
					}else{
						subDirectories.add(child);
					}
				}
			}
		}

		return new SubDirectoriesAndSize(total, subDirectories);
	}


	class SubDirectoriesAndSize {
		final public long size;
		final public List<File> subDirectories;
		public SubDirectoriesAndSize(long size, List<File> subDirectories) {
			super();
			this.size = size;
			this.subDirectories = subDirectories;
		}
	}

	
	public static void main(String[] args) throws Exception {		
		final long start = System.nanoTime();		
		final double total = new ConcurrentTotalFileSize().getTotalSizeOfFile(new File("F:"));
		final long end = System.nanoTime();
		System.out.println("Total Size in GB: " + ((( total / 1024 ) / 1024 ) / 1024 )) ;
		System.out.println("Time Taken: " + (end - start) / 1.0e9);
	}
			
	/*
	 * Total Size in GB: 82.32213742472231 
	 * Time Taken: 6.713654089
	 */	
	
	
}

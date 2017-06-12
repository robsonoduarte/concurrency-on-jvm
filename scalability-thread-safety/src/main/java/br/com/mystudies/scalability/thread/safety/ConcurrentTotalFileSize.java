package br.com.mystudies.scalability.thread.safety;

import static java.util.concurrent.Executors.newFixedThreadPool;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;

public class ConcurrentTotalFileSize {


	public double getTotalSizeOfFile(File path) {

		final ExecutorService service = newFixedThreadPool(100);


		try{
			long total = 0;
			final List<File> directories = new ArrayList<>();
			directories.add(path);

			while (!directories.isEmpty()) {

			}


			return 0;

		}finally {
			service.shutdown();
		}



	}









	private SubDirectoriesAndSize getTotalAndSubDirs(final File file){
		long total  = 0 ;
		final List<File> subDirectories = new ArrayList<>();
		if(file.isAbsolute()){
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

}

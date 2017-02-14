package br.com.mystudies.scalability.thread.safety;

import java.io.File;
import java.util.concurrent.ExecutorService;

public class NaivelyConcurrentTotalFileSize {

	public long getTotalSizeOfFile(File file) {
		return 0;
	}






	private long getTotalSizeOfFilesInDir(final ExecutorService service, final File file){

		if(file.isFile()) return file.length();





		return 0;
	}


}

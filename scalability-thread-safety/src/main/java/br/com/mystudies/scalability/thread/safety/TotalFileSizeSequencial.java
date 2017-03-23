package br.com.mystudies.scalability.thread.safety;

import static java.lang.System.nanoTime;

import java.io.File;
import java.io.IOException;

public class TotalFileSizeSequencial {



	public long getTotalSizeOfFilesInDir(File file) throws IOException {
		System.out.println(file.getName());

		if(file.isFile()) return file.length();

		File[] children = file.listFiles();
		long total = 0;
		if(children != null)
			for (File child : children) {
				total += getTotalSizeOfFilesInDir(child);
			}
		return total;
	}





	public static void main(String[] args) throws IOException {
		final long start = nanoTime();
		final long total = new TotalFileSizeSequencial().getTotalSizeOfFilesInDir(new File("E:/apps/"));
		final long end  = nanoTime();
		System.out.println("Total Size: " + total);
		System.out.println("Time taken: " + (end - start) / 1.0e9);
	}

	/*
	 *  Total Size: 66088184283
		Time taken: 256.787256693
	 */


}

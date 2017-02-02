package br.com.mystudies.scalability.thread.safety;

import static java.lang.System.nanoTime;
import static java.lang.System.out;
import static java.util.stream.Collectors.toList;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TotalFileSizeSequencial {



	public long getTotalSizeOfFilesInDir(File file) throws IOException {
		return Files.walk(Paths.get(file.toURI()))
				.peek(out::println)
				.collect(toList())
				.size();

	}




	public static void main(String[] args) throws IOException {

		final long start = nanoTime();
		final long total = new TotalFileSizeSequencial().getTotalSizeOfFilesInDir(new File("E:/"));
		final long end  = nanoTime();
		System.out.println("Total Size: " + total);
		System.out.println("Time taken: " + (end - start) / 1.0e9);
	}






}

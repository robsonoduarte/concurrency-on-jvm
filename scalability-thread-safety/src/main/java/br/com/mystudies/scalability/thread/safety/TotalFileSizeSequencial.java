package br.com.mystudies.scalability.thread.safety;

import static java.util.stream.Collectors.toList;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TotalFileSizeSequencial {



	public long getTotalSizeOfFilesInDir(File file) throws IOException {
		return Files.walk(Paths.get(file.toURI()))
				.collect(toList())
				.size();

	}







}

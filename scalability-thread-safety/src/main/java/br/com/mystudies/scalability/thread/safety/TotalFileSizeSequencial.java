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



	/*
	 * Exception in thread "main" java.io.UncheckedIOException: java.nio.file.AccessDeniedException: E:\$RECYCLE.BIN\S-1-5-21-726529361-1217131281-4128685623-1003
	at java.nio.file.FileTreeIterator.fetchNextIfNeeded(FileTreeIterator.java:88)
	at java.nio.file.FileTreeIterator.hasNext(FileTreeIterator.java:104)
	at java.util.Iterator.forEachRemaining(Iterator.java:115)
	at java.util.Spliterators$IteratorSpliterator.forEachRemaining(Spliterators.java:1801)
	at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:512)
	at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:502)
	at java.util.stream.ReduceOps$ReduceOp.evaluateSequential(ReduceOps.java:708)
	at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
	at java.util.stream.ReferencePipeline.collect(ReferencePipeline.java:499)
	at br.com.mystudies.scalability.thread.safety.TotalFileSizeSequencial.getTotalSizeOfFilesInDir(TotalFileSizeSequencial.java:19)
	at br.com.mystudies.scalability.thread.safety.TotalFileSizeSequencial.main(TotalFileSizeSequencial.java:30)
Caused by: java.nio.file.AccessDeniedException: E:\$RECYCLE.BIN\S-1-5-21-726529361-1217131281-4128685623-1003
	at sun.nio.fs.WindowsException.translateToIOException(WindowsException.java:83)
	at sun.nio.fs.WindowsException.rethrowAsIOException(WindowsException.java:97)
	at sun.nio.fs.WindowsException.rethrowAsIOException(WindowsException.java:102)
	at sun.nio.fs.WindowsDirectoryStream.<init>(WindowsDirectoryStream.java:86)
	at sun.nio.fs.WindowsFileSystemProvider.newDirectoryStream(WindowsFileSystemProvider.java:518)
	at java.nio.file.Files.newDirectoryStream(Files.java:457)
	at java.nio.file.FileTreeWalker.visit(FileTreeWalker.java:300)
	at java.nio.file.FileTreeWalker.next(FileTreeWalker.java:372)
	at java.nio.file.FileTreeIterator.fetchNextIfNeeded(FileTreeIterator.java:95)
	... 10 more
	 */




}

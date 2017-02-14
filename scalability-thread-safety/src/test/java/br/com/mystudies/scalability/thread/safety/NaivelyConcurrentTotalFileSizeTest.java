package br.com.mystudies.scalability.thread.safety;

import static org.junit.Assert.assertEquals;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

public class NaivelyConcurrentTotalFileSizeTest {


	private NaivelyConcurrentTotalFileSize naivelyConcurrent;


	@Before
	public void setUp() throws Exception {
		naivelyConcurrent = new NaivelyConcurrentTotalFileSize();
	}



	@Test
	public void test() throws Exception {
	  long total = naivelyConcurrent.getTotalSizeOfFile(path());
	  assertEquals(total, 12);
	}




	
	
	
	
	
	

	private File path() throws Exception {
		return new File(TotalFileSizeSequencialTest.class.getResource("/dir").toURI());
	}

}

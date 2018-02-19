package br.com.mystudies.scalability.thread.safety;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class ConcurrentForkJoinTotalFileSizeTest extends BaseTest{

	
	private ConcurrentForkJoinTotalFileSize concurrent;


	@Before	
	public void setUp() throws Exception {
		concurrent = new ConcurrentForkJoinTotalFileSize();
	}

	

	@Test
	public void test() throws Exception {
		double total = concurrent.getTotalSizeOfFile(path());
		assertEquals(4500,total,0.0);
	}
	
	
}

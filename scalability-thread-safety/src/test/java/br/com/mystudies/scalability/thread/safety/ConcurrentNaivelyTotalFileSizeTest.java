package br.com.mystudies.scalability.thread.safety;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class ConcurrentNaivelyTotalFileSizeTest extends BaseTest {


	private ConcurrentNaivelyTotalFileSize naivelyConcurrent;


	@Before
	public void setUp() throws Exception {
		naivelyConcurrent = new ConcurrentNaivelyTotalFileSize();
	}



	@Test
	public void test() throws Exception {
	  double total = naivelyConcurrent.getTotalSizeOfFile(path());
	  assertEquals(total, 4500,0.0);
	}













}

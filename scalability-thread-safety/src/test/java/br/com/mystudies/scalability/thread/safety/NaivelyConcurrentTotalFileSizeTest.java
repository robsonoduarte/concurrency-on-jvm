package br.com.mystudies.scalability.thread.safety;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class NaivelyConcurrentTotalFileSizeTest extends BaseTest {


	private NaivelyConcurrentTotalFileSize naivelyConcurrent;


	@Before
	public void setUp() throws Exception {
		naivelyConcurrent = new NaivelyConcurrentTotalFileSize();
	}



	@Test
	public void test() throws Exception {
	  double total = naivelyConcurrent.getTotalSizeOfFile(path());
	  assertEquals(total, 4500,0.0);
	}













}

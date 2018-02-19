package br.com.mystudies.scalability.thread.safety;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class ConcurrentCountDownLatchTotalFileSizeTest extends BaseTest{


	private ConcurrentCountDownLatchTotalFileSize concurrent;


	@Before
	public void setUp() throws Exception {
		concurrent = new ConcurrentCountDownLatchTotalFileSize();
	}


	@Test
	public void test() throws Exception {
		double total = concurrent.getTotalSizeOfFile(path());
		assertEquals(total, 4500.0, 0.0);
	}

}

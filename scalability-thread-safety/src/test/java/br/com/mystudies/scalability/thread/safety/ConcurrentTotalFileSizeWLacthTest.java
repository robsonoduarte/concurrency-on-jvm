package br.com.mystudies.scalability.thread.safety;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class ConcurrentTotalFileSizeWLacthTest extends BaseTest{


	private ConcurrentTotalFileSizeWLacth concurrent;


	@Before
	public void setUp() throws Exception {
		concurrent = new ConcurrentTotalFileSizeWLacth();
	}


	@Test
	public void test() throws Exception {
		double total = concurrent.getTotalSizeOfFile(path());
		assertEquals(total, 4500.0, 0.0);
	}



}

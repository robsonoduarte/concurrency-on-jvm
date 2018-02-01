package br.com.mystudies.scalability.thread.safety;

import static org.junit.Assert.assertEquals;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

public class ConcurrentTotalFileSizeWLacthTest {


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










	private File path() throws Exception {
		return new File(TotalFileSizeSequencialTest.class.getResource("/dir").toURI());
	}

}

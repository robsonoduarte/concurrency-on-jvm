package br.com.mystudies.scalability.thread.safety;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class TotalFileSizeSequencialTest extends BaseTest{

	private TotalFileSizeSequencial totalFileSizeSequencial;


	@Before
	public void setUp() throws Exception {
		totalFileSizeSequencial = new TotalFileSizeSequencial();
	}



	@Test
	public void test() throws Exception {
		double total = totalFileSizeSequencial.getTotalSizeOfFilesInDir(path());
		assertEquals(total, 4500.0, 0.0);
	}



}

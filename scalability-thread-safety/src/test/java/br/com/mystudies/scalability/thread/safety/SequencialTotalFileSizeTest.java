package br.com.mystudies.scalability.thread.safety;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class SequencialTotalFileSizeTest extends BaseTest{

	private SequencialTotalFileSize totalFileSizeSequencial;


	@Before
	public void setUp() throws Exception {
		totalFileSizeSequencial = new SequencialTotalFileSize();
	}



	@Test
	public void test() throws Exception {
		double total = totalFileSizeSequencial.getTotalSizeOfFile(path());
		assertEquals(total, 4500.0, 0.0);
	}



}

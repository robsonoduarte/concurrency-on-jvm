package br.com.mystudies.scalability.thread.safety;

import static org.junit.Assert.assertEquals;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

public class TotalFileSizeSequencialTest {

	private TotalFileSizeSequencial totalFileSizeSequencial;


	@Before
	public void setUp() throws Exception {
		totalFileSizeSequencial = new TotalFileSizeSequencial();
	}



	@Test
	public void test() throws Exception {
		System.out.println(temp().toURI());
		long total = totalFileSizeSequencial.getTotalSizeOfFilesInDir(temp());
		assertEquals(total, 12);
	}




























	private File temp() throws Exception {
		return new File(TotalFileSizeSequencialTest.class.getResource("/dir").toURI());
	}









}
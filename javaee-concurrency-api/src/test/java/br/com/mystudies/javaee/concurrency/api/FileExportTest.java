package br.com.mystudies.javaee.concurrency.api;

import org.junit.Before;
import org.junit.Test;

public class FileExportTest {
	
	private FileExport fileExport;


	@Before
	public void setUp() throws Exception {
		fileExport = new FileExport();
	}

	
	@Test
	public void test() {
		FileExportResult fileExportResult = fileExport.export(new ExportFilter());
	}

}

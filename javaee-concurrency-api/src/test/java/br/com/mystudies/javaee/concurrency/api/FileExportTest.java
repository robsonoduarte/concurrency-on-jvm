package br.com.mystudies.javaee.concurrency.api;

import org.junit.Before;
import org.junit.Test;

public class FileExportTest {

	private ExportFile fileExport;


	@Before
	public void setUp() throws Exception {
		fileExport = new ExportFileTXT();
	}


	@Test
	public void test() {
		FileExportResult fileExportResult = fileExport.export(new ExportFilter());
	}

}

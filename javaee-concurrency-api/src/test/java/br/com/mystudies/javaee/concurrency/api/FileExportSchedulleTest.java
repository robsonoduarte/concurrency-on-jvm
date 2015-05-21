package br.com.mystudies.javaee.concurrency.api;



import org.junit.Before;
import org.junit.Test;

public class FileExportSchedulleTest {

	private ExportFileSchedulle fileExportSchedulle;

	
	@Before
	public void setUp() throws Exception {
		fileExportSchedulle = new ExportFileSchedulle();
	}

	
	@Test
	public void test() {
		ExportFilter filter = fileExportSchedulle.exportFilter();
	}

	
}

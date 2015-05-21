package br.com.mystudies.javaee.concurrency.api;



import org.junit.Before;
import org.junit.Test;

public class FileExportSchedulleTest {

	private FileExportSchedulle fileExportSchedulle;

	
	@Before
	public void setUp() throws Exception {
		fileExportSchedulle = new FileExportSchedulle();
	}

	
	@Test
	public void test() {
		ExportFilter filter = fileExportSchedulle.exportFilter();
	}

	
}

package br.com.mystudies.javaee.concurrency.api;

import org.junit.Before;
import org.junit.Test;

public class FactoryExportFileTest {

	private FactoryExportFile factoryExportFile;

	@Before
	public void setUp() throws Exception {
		factoryExportFile = new FactoryExportFile();
	}

	@Test
	public void test() {
		ExportFile exportFile = factoryExportFile.create("Export");
	}

}

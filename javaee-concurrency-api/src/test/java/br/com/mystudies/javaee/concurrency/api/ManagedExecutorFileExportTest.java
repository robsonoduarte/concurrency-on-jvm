package br.com.mystudies.javaee.concurrency.api;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import javax.enterprise.concurrent.ManagedExecutorService;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

public class ManagedExecutorFileExportTest {


	@InjectMocks
	private ManagedExecutorFileExport managedExecutorFileExport;


	@Mock
	private ManagedExecutorService managedExecutorService;


	@Mock
	private FactoryExportFile factoryExportFile;


	@Mock
	private ExportFile exportFile;


	@Before
	public void setUp() throws Exception {
		managedExecutorFileExport = new ManagedExecutorFileExport();
		initMocks(this);
	}




	@Test
	public void test() throws InterruptedException {
		when(factoryExportFile.create("any")).thenReturn(exportFile, exportFile);
		/*when(managedExecutorService.invokeAll(asList(new FileExport()))*/
		managedExecutorFileExport.execute(new ExportFileSchedulle());
		verify(managedExecutorService).invokeAll(any());
	}



}

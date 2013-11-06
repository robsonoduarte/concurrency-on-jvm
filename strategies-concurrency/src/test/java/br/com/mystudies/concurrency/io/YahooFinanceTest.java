package br.com.mystudies.concurrency.io;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import java.io.IOException;
import java.io.InputStream;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;


public class YahooFinanceTest {

	
	@Mock
	YahooFinanceConnect yahooFinanceConnect; 

	
	
	@InjectMocks
	YahooFinance yahooFinance;
	
	
	
	@Before
	public void setUp() throws Exception {
		yahooFinance = new YahooFinance();
		initMocks(this);
	}
	
	
	
	@Test
	public void test() throws IOException {
		
		when(yahooFinanceConnect.connect(anyString())).thenReturn(getStream());				
				
		assertThat(yahooFinance.getLastClosePrice("goog"), equalTo(34.9));
		
		verify(yahooFinanceConnect).connect(anyString());
	}



	private InputStream getStream() {
		return getClass().getResourceAsStream("/table.cvs");
	}
		
}

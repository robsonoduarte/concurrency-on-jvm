package br.com.mystudies.concurrency.io.concurrent;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import br.com.mystudies.concurrency.io.BaseNetAssetValueTest;
import br.com.mystudies.concurrency.io.YahooFinance;

public class ConcurrentNetAssetValueTest extends BaseNetAssetValueTest{

	
	
	@Mock
	private YahooFinance yahooFinance;
	
	@InjectMocks
	private ConcurrentNetAssetValue concurrentNetAssetValue;

	
	@Before
	public void setUp() throws Exception {
		concurrentNetAssetValue = new ConcurrentNetAssetValue();
		initMocks(this);
	}
	
	
/*	stocks.put("AAPL", 2505);
	stocks.put("AMGN", 3406);
	stocks.put("AMZN", 9354);*/
	
	
	
	
	@Test
	public void test() throws IOException {
		when(yahooFinance.getLastClosePrice(anyString())).thenReturn(10.5, 20.4, 30.9);
		assertThat(concurrentNetAssetValue.compute(getStocks()), equalTo(384823.5));
		verify(yahooFinance, times(3)).getLastClosePrice(anyString());
	}

}

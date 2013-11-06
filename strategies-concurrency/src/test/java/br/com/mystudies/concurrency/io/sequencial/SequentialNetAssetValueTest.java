package br.com.mystudies.concurrency.io.sequencial;

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

public class SequentialNetAssetValueTest extends BaseNetAssetValueTest {

	
	@Mock
	private YahooFinance yahooFinance;
	
	
	@InjectMocks
	private SequentialNetAssetValue sequentialNetAssetValue;

	
	
	@Before
	public void setUp() throws Exception {
		sequentialNetAssetValue = new SequentialNetAssetValue();
		initMocks(this);
	}

	
	@Test
	public void test() throws IOException {
		when(yahooFinance.getLastClosePrice(anyString())).thenReturn(10.5, 20.4, 30.9);
		assertThat(sequentialNetAssetValue.compute(getStocks()), equalTo(384823.5));
		verify(yahooFinance, times(3)).getLastClosePrice(anyString());
	}

}

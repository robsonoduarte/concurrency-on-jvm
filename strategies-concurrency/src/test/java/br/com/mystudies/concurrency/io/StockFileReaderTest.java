package br.com.mystudies.concurrency.io;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

public class StockFileReaderTest {

	private StockFileReader stockFileReader;

	
	@Before
	public void setUp() throws Exception {
		stockFileReader = new StockFileReader();
	}
	
	

	
	@Test
	public void test() {
		assertThat(stockFileReader.reader(), equalTo(getStocks()));
	}



	private Map<String,Integer> getStocks() {
		
		Map<String, Integer> stocks = new HashMap<>();
		
		stocks.put("AAPL", 2505);
		stocks.put("AMGN", 3406);
		stocks.put("AMZN", 9354);
		stocks.put("BAC", 9839);
		stocks.put("BMY", 5099);
		stocks.put("CAT", 8463);
		
		return stocks;
	}
		
}

package br.com.mystudies.concurrency.io;

import java.util.Map;
import java.util.TreeMap;

public class BaseNetAssetValueTest {

	protected Map<String, Integer> getStocks() {
		
		Map<String, Integer> stocks = new TreeMap<>();
		
		stocks.put("AAPL", 2505);
		stocks.put("AMGN", 3406);
		stocks.put("AMZN", 9354);
		
		return stocks;		
	}

}
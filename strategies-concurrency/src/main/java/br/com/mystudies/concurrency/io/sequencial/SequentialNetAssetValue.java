package br.com.mystudies.concurrency.io.sequencial;

import java.io.IOException;
import java.util.Map;

import br.com.mystudies.concurrency.io.NetAssetValue;
import br.com.mystudies.concurrency.io.YahooFinance;

public class SequentialNetAssetValue implements NetAssetValue {


	private YahooFinance yahooFinance;
	
	
	
	public SequentialNetAssetValue() {
		yahooFinance = new YahooFinance();
	}



	@Override
	public Double compute(Map<String, Integer> stocks) throws IOException {
		
		Double netAssetValue = 0.0;
		
		for (String ticker : stocks.keySet()) {
			netAssetValue +=  stocks.get(ticker) * yahooFinance.getLastClosePrice(ticker);
		}
		
		return netAssetValue;
	}

}

package br.com.mystudies.concurrency.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class YahooFinance {

	
	private YahooFinanceConnect yahooFinanceConnect;

	
	public YahooFinance() {
		yahooFinanceConnect = new YahooFinanceConectCVS();
	}


	public Double getLastClosePrice(String ticker) throws IOException {
		return getClosedPrice(getFinanceLine(yahooFinanceConnect.connect(ticker)));
	}

	
	private String getFinanceLine(InputStream inputStream) throws IOException {		
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
		bufferedReader.readLine(); // <-- FIXME: horrible
		return bufferedReader.readLine();
	}


	private Double getClosedPrice(String line) {
		return Double.parseDouble(line.split(",")[6]);
	}

}

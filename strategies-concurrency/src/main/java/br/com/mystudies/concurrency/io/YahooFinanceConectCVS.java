package br.com.mystudies.concurrency.io;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class YahooFinanceConectCVS implements YahooFinanceConnect {

	
	@Override
	public InputStream connect(String ticker) throws IOException{
		return new URL("http://ichart.finance.yahoo.com/table.csv?s=" + ticker).openStream();
	}
	
	
}

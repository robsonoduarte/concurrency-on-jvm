package br.com.mystudies.concurrency.io;

import java.io.IOException;
import java.io.InputStream;

public interface YahooFinanceConnect {

	InputStream connect(String ticker) throws IOException;

}

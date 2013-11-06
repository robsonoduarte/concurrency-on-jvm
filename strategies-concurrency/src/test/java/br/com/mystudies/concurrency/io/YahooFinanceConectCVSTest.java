package br.com.mystudies.concurrency.io;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

public class YahooFinanceConectCVSTest {

	
	private YahooFinanceConectCVS yahooFinanceConectCVS;

	
	@Before
	public void setUp() throws Exception {
		yahooFinanceConectCVS = new YahooFinanceConectCVS();
	}

	
	
	@Test
	public void test() throws IOException {
		assertThat(yahooFinanceConectCVS.connect("AAPL"), notNullValue());
	}

	
}

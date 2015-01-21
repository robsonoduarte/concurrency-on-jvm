package br.com.mystudies.concurrency.io.concurrent;

import static java.lang.System.out;

import java.io.IOException;

import br.com.mystudies.concurrency.Apps;
import br.com.mystudies.concurrency.io.NetAssetValue;
import br.com.mystudies.concurrency.io.StockFileReader;

public class App extends Apps{

	public static void main(String[] args) throws IOException {

		start();

		NetAssetValue netAssetValue = new ConcurrentNetAssetValue();

		Double total = netAssetValue.compute(new StockFileReader().reader());

		out.println("The total of NAV is..: " + formatCurrency(total));

		finish();
	}


}

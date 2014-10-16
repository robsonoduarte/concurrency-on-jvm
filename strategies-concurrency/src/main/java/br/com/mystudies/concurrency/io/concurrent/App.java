package br.com.mystudies.concurrency.io.concurrent;

import static java.lang.System.currentTimeMillis;
import static java.lang.System.out;

import java.io.IOException;
import java.text.DecimalFormat;

import br.com.mystudies.concurrency.io.NetAssetValue;
import br.com.mystudies.concurrency.io.StockFileReader;

public class App {

	public static void main(String[] args) throws IOException {

		long start = currentTimeMillis();
	
		NetAssetValue netAssetValue = new ConcurrentNetAssetValue();
		
		Double total = netAssetValue.compute(new StockFileReader().reader());
		
		System.out.println(DecimalFormat.getCurrencyInstance().format(total));
		
		printTotalTime(start,currentTimeMillis());	
	}
	
	
	private static void printTotalTime(long start, long finish) {
		out.print("Total Time..: " +  ( finish - start) / 1000  + " seconds");
	}
	
}

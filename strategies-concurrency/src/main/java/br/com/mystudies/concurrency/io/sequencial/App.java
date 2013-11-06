package br.com.mystudies.concurrency.io.sequencial;

import static java.lang.System.currentTimeMillis;
import static java.lang.System.out;

import java.io.IOException;

import br.com.mystudies.concurrency.io.NetAssetValue;
import br.com.mystudies.concurrency.io.StockFileReader;

public class App {

	public static void main(String[] args) throws IOException {

		long start = currentTimeMillis();
	
		NetAssetValue netAssetValue = new SequentialNetAssetValue();
		
		netAssetValue.compute(new StockFileReader().reader());
		
		printTotalTime(start,currentTimeMillis());
		
	}

	
	
	private static void printTotalTime(long start, long finish) {
		out.print("Total Time..: " +  ( finish - start) / 1000  + " seconds");
	}
}

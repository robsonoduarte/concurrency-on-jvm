package br.com.mystudies.concurrency.io.concurrent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import br.com.mystudies.concurrency.io.NetAssetValue;
import br.com.mystudies.concurrency.io.YahooFinance;

public class ConcurrentNetAssetValue implements NetAssetValue {

	
	private YahooFinance yahooFinance;
	
	
	
	public ConcurrentNetAssetValue() {
		yahooFinance = new YahooFinance();
	}
	
	
	
	
	@Override
	public Double compute(final Map<String, Integer> stocks) throws IOException { // TODO: why need is final ?
		
			
		List<Callable<Double>> temp = new ArrayList<>();
		
		
		for (final String ticker : stocks.keySet()) { // TODO: why need is final ?
			
			temp.add( new Callable<Double>() {				
				@Override
				public Double call() throws Exception {
					 Double d =  stocks.get(ticker) * yahooFinance.getLastClosePrice(ticker);
					 System.out.println(d);
					return d; // TODO: why need is final ? 
				}
			});
			
		}
		
	     List<Future<Double>> temp2 =  tempInvokeAll(temp);
		
		
		return getTempTotal(temp2);
	}



	
	



	private List<Future<Double>> tempInvokeAll(List<Callable<Double>> temp) {
		
		try {
			ExecutorService executorService = Executors.newFixedThreadPool(getPoolSize());
			return executorService.invokeAll(temp, 10000, TimeUnit.SECONDS);
		} catch (Exception exception) {
			// TODO: handle exception
			return null;
		}
	}


	
	
	
	private int getPoolSize() {
		return (int) (Runtime.getRuntime().availableProcessors() / 0.1) ;
	}




	private Double getTempTotal(List<Future<Double>> temp2) {
		
		try {
			
			double total = 0.0;
			
			for (Future<Double> future : temp2) {
				total += future.get();
			}
			
			return total;
			
		} catch (Exception exception) {
			// TODO: handle exception
			return 0.0;
		}
	}
}

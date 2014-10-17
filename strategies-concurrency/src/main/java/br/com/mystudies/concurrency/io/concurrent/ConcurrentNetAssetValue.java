package br.com.mystudies.concurrency.io.concurrent;

import static java.util.concurrent.TimeUnit.SECONDS;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import br.com.mystudies.concurrency.io.NetAssetValue;
import br.com.mystudies.concurrency.io.YahooFinance;


public class ConcurrentNetAssetValue implements NetAssetValue {


	private YahooFinance yahooFinance;



	public ConcurrentNetAssetValue() {
		yahooFinance = new YahooFinance();
	}



	@Override
	public Double compute(final Map<String, Integer> stocks) throws IOException { // TODO: why need is final ?


		List<Callable<Double>> calllables = new ArrayList<>();


		for (final String ticker : stocks.keySet()) { // TODO: why need is final ?

			calllables.add( new Callable<Double>() {
				@Override
				public Double call() throws Exception {
					return stocks.get(ticker) * yahooFinance.getLastClosePrice(ticker); // TODO: why need is final ?
				}
			});
		}

		return sum(invokeAll(calllables));
	}








	private List<Future<Double>> invokeAll(List<Callable<Double>> callables) {

		try {
			ExecutorService executorService = Executors.newFixedThreadPool(getPoolSize());
			List<Future<Double>> list = executorService.invokeAll(callables, 10000, SECONDS);
			executorService.shutdown();
			return list;
		} catch (Exception exception) {
			// TODO: handle exception
			return null;
		}
	}





	private int getPoolSize() {
		return (int) (Runtime.getRuntime().availableProcessors() / 0.1) ;
	}




	private Double sum(List<Future<Double>> futures) {

		try {

			double total = 0.0;

			for (Future<Double> future : futures) {
				total += future.get();
			}

			return total;

		} catch (Exception exception) {
			// TODO: handle exception
			return 0.0;
		}
	}


}

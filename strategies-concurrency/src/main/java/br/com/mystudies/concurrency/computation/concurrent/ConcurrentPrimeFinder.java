package br.com.mystudies.concurrency.computation.concurrent;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.apache.commons.collections4.ListUtils.partition;
import static org.apache.commons.math3.primes.Primes.isPrime;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import br.com.mystudies.concurrency.computation.PrimeFinder;

public class ConcurrentPrimeFinder implements PrimeFinder {



	@Override
	public long countsPrimes(List<Integer> numbers) {

		List<Callable<Long>> callables = new ArrayList<>();

		List<List<Integer>> range = partitionRange(numbers);

		for (List<Integer> list : range) {

			callables.add(new Callable<Long>() {
				@Override
				public Long call() throws Exception {
					return list.stream()
							.filter(n -> isPrime(n))
							.count();
				}
			});
		}

		return sum(invokeAll(callables));
	}





	private List<List<Integer>> partitionRange(List<Integer> numbers) {
		return partition(numbers, getPoolSize() );
	}



	private int getPoolSize() {
		return (int) (Runtime.getRuntime().availableProcessors() ) ;
	}




	private List<Future<Long>> invokeAll(List<Callable<Long>> callables) {
		try {
			ExecutorService executorService = Executors.newFixedThreadPool(getPoolSize());
			List<Future<Long>> list = executorService.invokeAll(callables, 10000, SECONDS);
			executorService.shutdown();
			return list;
		} catch (Exception exception) {
			return null;
		}
	}





	// TODO: verify why I aren't should use stream API.
	private long sum(List<Future<Long>> invokeAll) {
/*		try {
			return invokeAll.stream().mapToLong(f -> f.get()).sum();
		} catch (Exception exception) {
			return 0;
		}*/

		try {
		/*return invokeAll.forEach(f -> f.get());*/
			long sum  = 0;

			for (Future<Long> future : invokeAll) {
					sum += future.get();
			}

			return sum;

		} catch (InterruptedException | ExecutionException exception) {
			return 0;
		}
	}

}

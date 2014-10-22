package br.com.mystudies.concurrency.computation.concurrent;

import static java.util.concurrent.TimeUnit.SECONDS;
import static java.util.stream.Collectors.toList;
import static org.apache.commons.collections4.ListUtils.partition;
import static org.apache.commons.math3.primes.Primes.isPrime;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Stream;

import br.com.mystudies.concurrency.computation.PrimeFinder;

public class ConcurrentPrimeFinder implements PrimeFinder {




	@Override
	public long countsPrimes(long finalNumber) {

		List<Callable<Long>> callables = new ArrayList<>();

		for (List<Integer> list : partitionRange(finalNumber)) {

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

















	private List<List<Integer>> partitionRange(long finalNumber) {
		return partition(createRange(finalNumber), getNumberOfProcessors());
	}





	private List<Integer> createRange(long finalNumber) {
		return Stream.iterate(1, n -> n + 1 )
				.limit(finalNumber)
				.collect(toList());
	}






	private int getNumberOfProcessors() {
		return Runtime.getRuntime().availableProcessors();
	}





	private List<Future<Long>> invokeAll(List<Callable<Long>> callables) {
		try {
			ExecutorService executorService = Executors.newFixedThreadPool(getNumberOfProcessors());
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

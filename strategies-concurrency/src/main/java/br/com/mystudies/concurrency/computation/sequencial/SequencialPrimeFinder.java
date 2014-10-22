package br.com.mystudies.concurrency.computation.sequencial;

import static org.apache.commons.math3.primes.Primes.isPrime;

import java.util.List;

import br.com.mystudies.concurrency.computation.PrimeFinder;

public class SequencialPrimeFinder implements PrimeFinder {

	@Override
	public long countsPrimes(List<Integer> numbers) {
		return numbers.stream()
				.filter(n -> isPrime(n))
				.count();
	}

}
